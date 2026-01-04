import { useRef, useEffect, useState } from 'react'
import { motion } from 'framer-motion'

const CollisionSim = ({ onBack }) => {
    const canvasRef = useRef(null)
    const animationRef = useRef(null)
    const [isRunning, setIsRunning] = useState(false)

    // Physics parameters
    const [params, setParams] = useState({
        mass1: 10,
        velX1: 8,
        velY1: 2,
        mass2: 10,
        velX2: -8,
        velY2: -2,
        elasticity: 0.8
    })

    // Bodies state
    const bodiesRef = useRef({
        red: { x: 200, y: 300, vx: 0, vy: 0, radius: 50, mass: 10 },
        blue: { x: 800, y: 300, vx: 0, vy: 0, radius: 50, mass: 10 }
    })

    // Physics loop
    useEffect(() => {
        const canvas = canvasRef.current
        if (!canvas) return
        const ctx = canvas.getContext('2d')

        const animate = () => {
            const bodies = bodiesRef.current
            const width = canvas.width
            const height = canvas.height

            // Clear
            ctx.fillStyle = '#0a0a0a'
            ctx.fillRect(0, 0, width, height)

            // Grid
            ctx.strokeStyle = 'rgba(255,255,255,0.04)'
            ctx.lineWidth = 1
            for (let i = 0; i < width; i += 50) {
                ctx.beginPath()
                ctx.moveTo(i, 0)
                ctx.lineTo(i, height)
                ctx.stroke()
            }
            for (let j = 0; j < height; j += 50) {
                ctx.beginPath()
                ctx.moveTo(0, j)
                ctx.lineTo(width, j)
                ctx.stroke()
            }

            if (isRunning) {
                // Update positions
                bodies.red.x += bodies.red.vx
                bodies.red.y += bodies.red.vy
                bodies.blue.x += bodies.blue.vx
                bodies.blue.y += bodies.blue.vy

                // Wall bounces
                for (const key of ['red', 'blue']) {
                    const b = bodies[key]
                    if (b.x - b.radius <= 0 || b.x + b.radius >= width) {
                        b.vx *= -0.95
                        b.x = b.x - b.radius <= 0 ? b.radius : width - b.radius
                    }
                    if (b.y - b.radius <= 0 || b.y + b.radius >= height) {
                        b.vy *= -0.95
                        b.y = b.y - b.radius <= 0 ? b.radius : height - b.radius
                    }
                }

                // Circle collision
                const dx = bodies.blue.x - bodies.red.x
                const dy = bodies.blue.y - bodies.red.y
                const dist = Math.sqrt(dx * dx + dy * dy)
                const minDist = bodies.red.radius + bodies.blue.radius

                if (dist < minDist && dist > 0) {
                    const nx = dx / dist
                    const ny = dy / dist
                    const overlap = minDist - dist

                    bodies.red.x -= nx * overlap / 2
                    bodies.red.y -= ny * overlap / 2
                    bodies.blue.x += nx * overlap / 2
                    bodies.blue.y += ny * overlap / 2

                    const v1n = bodies.red.vx * nx + bodies.red.vy * ny
                    const v2n = bodies.blue.vx * nx + bodies.blue.vy * ny

                    const m1 = bodies.red.mass
                    const m2 = bodies.blue.mass

                    const v1nAfter = ((v1n * (m1 - m2) + 2 * m2 * v2n) / (m1 + m2)) * params.elasticity
                    const v2nAfter = ((v2n * (m2 - m1) + 2 * m1 * v1n) / (m1 + m2)) * params.elasticity

                    bodies.red.vx += (v1nAfter - v1n) * nx
                    bodies.red.vy += (v1nAfter - v1n) * ny
                    bodies.blue.vx += (v2nAfter - v2n) * nx
                    bodies.blue.vy += (v2nAfter - v2n) * ny
                }
            }

            // Draw bodies
            const drawBody = (b, color, isRed) => {
                // Outer glow
                const gradient = ctx.createRadialGradient(b.x, b.y, b.radius * 0.5, b.x, b.y, b.radius * 2.5)
                gradient.addColorStop(0, color)
                gradient.addColorStop(1, 'transparent')
                ctx.fillStyle = gradient
                ctx.beginPath()
                ctx.arc(b.x, b.y, b.radius * 2, 0, Math.PI * 2)
                ctx.fill()

                // Main circle
                ctx.fillStyle = color
                ctx.beginPath()
                ctx.arc(b.x, b.y, b.radius, 0, Math.PI * 2)
                ctx.fill()

                // Inner highlight
                ctx.fillStyle = 'rgba(255,255,255,0.4)'
                ctx.beginPath()
                ctx.arc(b.x - b.radius * 0.3, b.y - b.radius * 0.3, b.radius * 0.35, 0, Math.PI * 2)
                ctx.fill()

                // Velocity arrow when not running
                if (!isRunning) {
                    const velX = isRed ? params.velX1 : params.velX2
                    const velY = isRed ? params.velY1 : params.velY2
                    const mag = Math.sqrt(velX * velX + velY * velY)
                    if (mag > 0.5) {
                        const scale = 4
                        const endX = b.x + velX * scale
                        const endY = b.y + velY * scale

                        ctx.strokeStyle = 'rgba(255,255,255,0.8)'
                        ctx.lineWidth = 3
                        ctx.beginPath()
                        ctx.moveTo(b.x, b.y)
                        ctx.lineTo(endX, endY)
                        ctx.stroke()

                        // Arrow head
                        const angle = Math.atan2(velY, velX)
                        ctx.beginPath()
                        ctx.moveTo(endX, endY)
                        ctx.lineTo(endX - 12 * Math.cos(angle - 0.4), endY - 12 * Math.sin(angle - 0.4))
                        ctx.lineTo(endX - 12 * Math.cos(angle + 0.4), endY - 12 * Math.sin(angle + 0.4))
                        ctx.closePath()
                        ctx.fillStyle = 'rgba(255,255,255,0.8)'
                        ctx.fill()
                    }
                }
            }

            drawBody(bodies.red, '#ff4757', true)
            drawBody(bodies.blue, '#1e90ff', false)

            animationRef.current = requestAnimationFrame(animate)
        }

        animate()
        return () => cancelAnimationFrame(animationRef.current)
    }, [isRunning, params])

    const startSimulation = () => {
        const bodies = bodiesRef.current
        bodies.red.vx = params.velX1 / 2
        bodies.red.vy = params.velY1 / 2
        bodies.blue.vx = params.velX2 / 2
        bodies.blue.vy = params.velY2 / 2
        setIsRunning(true)
    }

    const resetSimulation = () => {
        setIsRunning(false)
        const bodies = bodiesRef.current
        bodies.red = {
            x: 200,
            y: 300,
            vx: 0,
            vy: 0,
            radius: params.mass1 * 3 + 20,
            mass: params.mass1
        }
        bodies.blue = {
            x: 800,
            y: 300,
            vx: 0,
            vy: 0,
            radius: params.mass2 * 3 + 20,
            mass: params.mass2
        }
    }

    return (
        <div className="min-h-screen p-4">
            {/* Header */}
            <div className="flex items-center justify-between mb-4">
                <motion.button
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                    onClick={onBack}
                    className="px-6 py-3 bg-[#1a1a1a] border border-gray-700 rounded-xl text-gray-300 hover:text-white hover:border-cyan-500/50 transition-all text-lg font-semibold"
                >
                    ← MENU
                </motion.button>
                <h1 className="text-3xl font-bold text-cyan-400">PHYSICS SIMULATION CORE</h1>
                <div className="w-32" />
            </div>

            <div className="flex gap-4">
                {/* Canvas - Much larger */}
                <motion.div
                    initial={{ scale: 0.95, opacity: 0 }}
                    animate={{ scale: 1, opacity: 1 }}
                    className="flex-1"
                >
                    <canvas
                        ref={canvasRef}
                        width={1000}
                        height={600}
                        className="w-full rounded-2xl border-2 border-gray-800"
                    />
                </motion.div>

                {/* Controls - Side panel */}
                <motion.div
                    initial={{ x: 30, opacity: 0 }}
                    animate={{ x: 0, opacity: 1 }}
                    transition={{ delay: 0.1 }}
                    className="w-96 space-y-4"
                >
                    {/* Red Entity */}
                    <div className="p-5 bg-[#141414] rounded-2xl border border-red-900/40">
                        <h3 className="text-red-400 font-bold text-xl mb-4 flex items-center gap-2">
                            <span className="w-4 h-4 rounded-full bg-red-500" />
                            RED ENTITY
                        </h3>
                        <div className="space-y-4">
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">MASS</span>
                                    <span className="text-white font-bold">{params.mass1} kg</span>
                                </div>
                                <input
                                    type="range"
                                    min="5"
                                    max="25"
                                    value={params.mass1}
                                    onChange={(e) => setParams({ ...params, mass1: Number(e.target.value) })}
                                    className="w-full h-2 rounded-full appearance-none bg-gray-800 accent-red-500"
                                />
                            </div>
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">HORIZONTAL (X)</span>
                                    <span className="text-white font-bold">{params.velX1} m/s</span>
                                </div>
                                <input
                                    type="range"
                                    min="-20"
                                    max="20"
                                    value={params.velX1}
                                    onChange={(e) => setParams({ ...params, velX1: Number(e.target.value) })}
                                    className="w-full h-2 rounded-full appearance-none bg-gray-800 accent-red-500"
                                />
                            </div>
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">VERTICAL (Y)</span>
                                    <span className="text-white font-bold">{params.velY1} m/s</span>
                                </div>
                                <input
                                    type="range"
                                    min="-20"
                                    max="20"
                                    value={params.velY1}
                                    onChange={(e) => setParams({ ...params, velY1: Number(e.target.value) })}
                                    className="w-full h-2 rounded-full appearance-none bg-gray-800 accent-red-500"
                                />
                            </div>
                        </div>
                    </div>

                    {/* Blue Entity */}
                    <div className="p-5 bg-[#141414] rounded-2xl border border-blue-900/40">
                        <h3 className="text-blue-400 font-bold text-xl mb-4 flex items-center gap-2">
                            <span className="w-4 h-4 rounded-full bg-blue-500" />
                            BLUE ENTITY
                        </h3>
                        <div className="space-y-4">
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">MASS</span>
                                    <span className="text-white font-bold">{params.mass2} kg</span>
                                </div>
                                <input
                                    type="range"
                                    min="5"
                                    max="25"
                                    value={params.mass2}
                                    onChange={(e) => setParams({ ...params, mass2: Number(e.target.value) })}
                                    className="w-full h-2 rounded-full appearance-none bg-gray-800 accent-blue-500"
                                />
                            </div>
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">HORIZONTAL (X)</span>
                                    <span className="text-white font-bold">{params.velX2} m/s</span>
                                </div>
                                <input
                                    type="range"
                                    min="-20"
                                    max="20"
                                    value={params.velX2}
                                    onChange={(e) => setParams({ ...params, velX2: Number(e.target.value) })}
                                    className="w-full h-2 rounded-full appearance-none bg-gray-800 accent-blue-500"
                                />
                            </div>
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">VERTICAL (Y)</span>
                                    <span className="text-white font-bold">{params.velY2} m/s</span>
                                </div>
                                <input
                                    type="range"
                                    min="-20"
                                    max="20"
                                    value={params.velY2}
                                    onChange={(e) => setParams({ ...params, velY2: Number(e.target.value) })}
                                    className="w-full h-2 rounded-full appearance-none bg-gray-800 accent-blue-500"
                                />
                            </div>
                        </div>
                    </div>

                    {/* Elasticity */}
                    <div className="p-5 bg-[#141414] rounded-2xl border border-cyan-900/40">
                        <div className="flex justify-between items-center mb-3">
                            <h3 className="text-cyan-400 font-bold text-xl">ELASTICITY</h3>
                            <span className="text-2xl font-bold text-white">{(params.elasticity * 100).toFixed(0)}%</span>
                        </div>
                        <input
                            type="range"
                            min="0"
                            max="100"
                            value={params.elasticity * 100}
                            onChange={(e) => setParams({ ...params, elasticity: Number(e.target.value) / 100 })}
                            className="w-full h-2 rounded-full appearance-none bg-gray-800 accent-cyan-500"
                        />
                    </div>

                    {/* Actions */}
                    <div className="flex gap-3">
                        <motion.button
                            whileHover={{ scale: 1.02 }}
                            whileTap={{ scale: 0.98 }}
                            onClick={startSimulation}
                            disabled={isRunning}
                            className={`flex-1 py-4 rounded-xl font-bold text-lg ${isRunning
                                ? 'bg-gray-800 text-gray-500 cursor-not-allowed'
                                : 'bg-gradient-to-r from-cyan-500 to-blue-600 text-white'
                                }`}
                        >
                            ▶ EXECUTE
                        </motion.button>
                        <motion.button
                            whileHover={{ scale: 1.02 }}
                            whileTap={{ scale: 0.98 }}
                            onClick={resetSimulation}
                            className="flex-1 py-4 bg-[#1a1a1a] border-2 border-gray-700 rounded-xl font-bold text-lg text-gray-300 hover:text-white hover:border-gray-500"
                        >
                            ↻ RESET
                        </motion.button>
                    </div>

                    <p className="text-gray-600 text-xs text-center">
                        Adjust X & Y velocity to control direction • Mass applies on RESET
                    </p>
                </motion.div>
            </div>
        </div>
    )
}

export default CollisionSim
