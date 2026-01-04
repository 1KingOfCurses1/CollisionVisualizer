import { useRef, useEffect, useState } from 'react'
import { motion } from 'framer-motion'

const GravitySim = ({ onBack }) => {
    const canvasRef = useRef(null)
    const animationRef = useRef(null)
    const [isRunning, setIsRunning] = useState(true)

    const [params, setParams] = useState({
        sunMass: 1000,
        planetMass: 10,
        orbitRadius: 200,
        orbitSpeed: 4
    })

    // Physics bodies stored in ref
    const bodiesRef = useRef({
        sun: { x: 500, y: 300, mass: 1000 },
        planet: { x: 700, y: 300, vx: 0, vy: 4, mass: 10 }
    })
    const trailRef = useRef([])

    // Initialize orbit on param change
    useEffect(() => {
        if (!isRunning) {
            resetOrbit()
        }
    }, [params])

    const resetOrbit = () => {
        const bodies = bodiesRef.current
        bodies.sun = { x: 500, y: 300, mass: params.sunMass }
        bodies.planet = {
            x: 500 + params.orbitRadius,
            y: 300,
            vx: 0,
            vy: params.orbitSpeed,
            mass: params.planetMass
        }
        trailRef.current = []
    }

    useEffect(() => {
        const canvas = canvasRef.current
        if (!canvas) return
        const ctx = canvas.getContext('2d')
        const width = canvas.width
        const height = canvas.height

        // Gravitational constant (scaled for simulation)
        const G = 1

        const animate = () => {
            const bodies = bodiesRef.current

            // Semi-transparent clear for trail effect
            ctx.fillStyle = 'rgba(10, 10, 10, 0.15)'
            ctx.fillRect(0, 0, width, height)

            if (isRunning) {
                // Calculate gravitational force: F = G*m1*m2/r²
                const dx = bodies.sun.x - bodies.planet.x
                const dy = bodies.sun.y - bodies.planet.y
                const distSq = dx * dx + dy * dy
                const dist = Math.sqrt(distSq)

                if (dist > 35) {  // Prevent singularity when too close
                    // Force magnitude: F = G*M*m/r²
                    // Acceleration: a = F/m = G*M/r²
                    const accel = G * bodies.sun.mass / distSq

                    // Normalize direction and apply acceleration
                    const ax = accel * (dx / dist)
                    const ay = accel * (dy / dist)

                    // Update velocity (v = v + a*dt)
                    bodies.planet.vx += ax
                    bodies.planet.vy += ay

                    // Update position (x = x + v*dt)
                    bodies.planet.x += bodies.planet.vx
                    bodies.planet.y += bodies.planet.vy
                }

                // Record trail (limit length)
                trailRef.current.push({ x: bodies.planet.x, y: bodies.planet.y })
                if (trailRef.current.length > 300) trailRef.current.shift()
            }

            // Draw orbit trail
            if (trailRef.current.length > 1) {
                ctx.beginPath()
                ctx.moveTo(trailRef.current[0].x, trailRef.current[0].y)
                for (let i = 1; i < trailRef.current.length; i++) {
                    ctx.lineTo(trailRef.current[i].x, trailRef.current[i].y)
                }
                ctx.strokeStyle = 'rgba(30, 144, 255, 0.5)'
                ctx.lineWidth = 2
                ctx.stroke()
            }

            // Draw sun with corona glow
            const sunRadius = 30 + Math.log10(bodies.sun.mass) * 5

            // Outer glow
            const sunGlow = ctx.createRadialGradient(bodies.sun.x, bodies.sun.y, sunRadius,
                bodies.sun.x, bodies.sun.y, sunRadius * 3)
            sunGlow.addColorStop(0, 'rgba(255, 200, 0, 0.5)')
            sunGlow.addColorStop(0.5, 'rgba(255, 100, 0, 0.2)')
            sunGlow.addColorStop(1, 'transparent')
            ctx.fillStyle = sunGlow
            ctx.beginPath()
            ctx.arc(bodies.sun.x, bodies.sun.y, sunRadius * 3, 0, Math.PI * 2)
            ctx.fill()

            // Sun body
            const sunGrad = ctx.createRadialGradient(bodies.sun.x, bodies.sun.y, 0,
                bodies.sun.x, bodies.sun.y, sunRadius)
            sunGrad.addColorStop(0, '#ffffaa')
            sunGrad.addColorStop(0.5, '#ffdd00')
            sunGrad.addColorStop(1, '#ff8800')
            ctx.fillStyle = sunGrad
            ctx.beginPath()
            ctx.arc(bodies.sun.x, bodies.sun.y, sunRadius, 0, Math.PI * 2)
            ctx.fill()

            // Draw planet
            const planetRadius = 8 + bodies.planet.mass * 0.3

            // Planet glow
            const planetGlow = ctx.createRadialGradient(bodies.planet.x, bodies.planet.y, planetRadius * 0.5,
                bodies.planet.x, bodies.planet.y, planetRadius * 2)
            planetGlow.addColorStop(0, '#1e90ff')
            planetGlow.addColorStop(1, 'transparent')
            ctx.fillStyle = planetGlow
            ctx.beginPath()
            ctx.arc(bodies.planet.x, bodies.planet.y, planetRadius * 2, 0, Math.PI * 2)
            ctx.fill()

            // Planet body
            ctx.fillStyle = '#1e90ff'
            ctx.beginPath()
            ctx.arc(bodies.planet.x, bodies.planet.y, planetRadius, 0, Math.PI * 2)
            ctx.fill()

            // Stats
            const speed = Math.sqrt(bodies.planet.vx ** 2 + bodies.planet.vy ** 2)
            const currentDist = Math.sqrt((bodies.planet.x - bodies.sun.x) ** 2 + (bodies.planet.y - bodies.sun.y) ** 2)
            ctx.fillStyle = 'white'
            ctx.font = '14px Inter, sans-serif'
            ctx.fillText(`Distance: ${currentDist.toFixed(0)} units`, 20, 30)
            ctx.fillText(`Speed: ${speed.toFixed(2)} units/s`, 20, 50)
            ctx.fillText(`Orbital Period: ${(2 * Math.PI * currentDist / speed).toFixed(1)} s`, 20, 70)

            animationRef.current = requestAnimationFrame(animate)
        }

        animate()
        return () => cancelAnimationFrame(animationRef.current)
    }, [isRunning])

    return (
        <div className="min-h-screen p-4">
            <div className="flex items-center justify-between mb-4">
                <motion.button whileHover={{ scale: 1.05 }} whileTap={{ scale: 0.95 }}
                    onClick={onBack}
                    className="px-6 py-3 bg-[#111] border border-gray-800 rounded-xl text-gray-400 hover:text-white transition-all font-semibold"
                >
                    ← BACK
                </motion.button>
                <h1 className="text-3xl font-bold">
                    <span className="text-purple-400">🪐 Gravitational Fields</span>
                </h1>
                <div className="w-32" />
            </div>

            <div className="flex gap-4">
                <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="flex-1">
                    <canvas ref={canvasRef} width={1000} height={600} className="w-full rounded-2xl border-2 border-gray-800" />
                </motion.div>

                <motion.div initial={{ x: 30, opacity: 0 }} animate={{ x: 0, opacity: 1 }} className="w-80 space-y-4">
                    <div className="p-5 bg-[#111] rounded-2xl border border-purple-900/40">
                        <h3 className="text-purple-400 font-bold text-xl mb-4">ORBITAL PARAMETERS</h3>

                        <div className="space-y-4">
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Sun Mass (M)</span>
                                    <span className="text-white font-bold">{params.sunMass}</span>
                                </div>
                                <input type="range" min="500" max="3000" step="100" value={params.sunMass}
                                    onChange={(e) => setParams({ ...params, sunMass: Number(e.target.value) })}
                                    className="w-full accent-yellow-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Planet Mass (m)</span>
                                    <span className="text-white font-bold">{params.planetMass}</span>
                                </div>
                                <input type="range" min="1" max="50" value={params.planetMass}
                                    onChange={(e) => setParams({ ...params, planetMass: Number(e.target.value) })}
                                    className="w-full accent-blue-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Initial Orbit Radius</span>
                                    <span className="text-white font-bold">{params.orbitRadius}</span>
                                </div>
                                <input type="range" min="100" max="350" value={params.orbitRadius}
                                    onChange={(e) => setParams({ ...params, orbitRadius: Number(e.target.value) })}
                                    className="w-full accent-purple-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Initial Speed</span>
                                    <span className="text-white font-bold">{params.orbitSpeed}</span>
                                </div>
                                <input type="range" min="1" max="10" step="0.5" value={params.orbitSpeed}
                                    onChange={(e) => setParams({ ...params, orbitSpeed: Number(e.target.value) })}
                                    className="w-full accent-purple-500"
                                />
                            </div>
                        </div>
                    </div>

                    <div className="p-4 bg-[#111] rounded-2xl border border-gray-800">
                        <h4 className="text-gray-400 text-sm mb-2">NEWTON'S LAW OF GRAVITATION</h4>
                        <p className="text-lg text-purple-300 font-mono">F = G·M·m / r²</p>
                        <p className="text-xs text-gray-500 mt-2">The gravitational force attracts the planet toward the sun, creating orbital motion.</p>
                    </div>

                    <div className="flex gap-3">
                        <motion.button whileHover={{ scale: 1.02 }} whileTap={{ scale: 0.98 }}
                            onClick={() => setIsRunning(!isRunning)}
                            className={`flex-1 py-4 rounded-xl font-bold text-lg ${isRunning ? 'bg-orange-600 text-white' : 'bg-gradient-to-r from-purple-500 to-pink-600 text-white'}`}
                        >
                            {isRunning ? '⏸ PAUSE' : '▶ START'}
                        </motion.button>
                        <motion.button whileHover={{ scale: 1.02 }} whileTap={{ scale: 0.98 }}
                            onClick={() => { setIsRunning(false); resetOrbit(); }}
                            className="flex-1 py-4 bg-[#111] border-2 border-gray-700 rounded-xl font-bold text-lg text-gray-300 hover:text-white"
                        >
                            ↻ RESET
                        </motion.button>
                    </div>

                    <p className="text-xs text-gray-600 text-center">
                        Tip: Try setting speed to √(G·M/r) for a circular orbit
                    </p>
                </motion.div>
            </div>
        </div>
    )
}

export default GravitySim
