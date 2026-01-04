import { useRef, useEffect, useState } from 'react'
import { motion } from 'framer-motion'

const ProjectileSim = ({ onBack }) => {
    const canvasRef = useRef(null)
    const animationRef = useRef(null)
    const [isRunning, setIsRunning] = useState(false)

    // Slider params (only apply on launch)
    const [params, setParams] = useState({
        velocity: 40,
        angle: 45,
        height: 20,
        gravity: 9.8
    })

    // Active simulation params (set on launch, don't change during sim)
    const activeParamsRef = useRef({ ...params })

    const [stats, setStats] = useState({ range: 0, maxHeight: 0, time: 0 })

    const projectileRef = useRef({ x: 0, y: 0, vx: 0, vy: 0, time: 0, maxY: 0, landed: false })
    const trailRef = useRef([])

    // Constants
    const SCALE = 4  // pixels per meter
    const GROUND_Y = 490  // fixed ground position
    const LAUNCH_X = 80

    useEffect(() => {
        const canvas = canvasRef.current
        if (!canvas) return
        const ctx = canvas.getContext('2d')
        const width = canvas.width
        const height = canvas.height

        const animate = () => {
            const activeParams = activeParamsRef.current
            const platformHeight = activeParams.height * SCALE

            // Clear
            ctx.fillStyle = '#0a0a0a'
            ctx.fillRect(0, 0, width, height)

            // Grid
            ctx.strokeStyle = 'rgba(255,255,255,0.03)'
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

            // Ground
            ctx.fillStyle = '#1a3a1a'
            ctx.fillRect(0, GROUND_Y, width, height - GROUND_Y)
            ctx.strokeStyle = '#2d5a2d'
            ctx.lineWidth = 2
            ctx.beginPath()
            ctx.moveTo(0, GROUND_Y)
            ctx.lineTo(width, GROUND_Y)
            ctx.stroke()

            // Launch platform (using active params, not slider)
            ctx.fillStyle = '#555'
            ctx.fillRect(LAUNCH_X - 25, GROUND_Y - platformHeight, 50, platformHeight)
            ctx.fillStyle = '#777'
            ctx.fillRect(LAUNCH_X - 25, GROUND_Y - platformHeight, 50, 5)

            const p = projectileRef.current

            if (isRunning && !p.landed) {
                const dt = 0.04  // time step

                // Apply gravity: vy decreases (becomes more negative going down)
                p.vy -= activeParams.gravity * dt

                // Update position
                p.x += p.vx * dt
                p.y += p.vy * dt

                p.time += dt

                // Track max height (y is height above launch platform)
                if (p.y > p.maxY) p.maxY = p.y

                // Record trail
                const screenX = LAUNCH_X + p.x * SCALE
                const screenY = GROUND_Y - platformHeight - p.y * SCALE
                trailRef.current.push({ x: screenX, y: screenY })

                // Check if hit ground (projectile y goes below -platformHeight in meters)
                // Ground is at y = -activeParams.height (relative to launch platform)
                if (p.y < -activeParams.height) {
                    p.landed = true
                    p.y = -activeParams.height  // clamp to ground
                    setIsRunning(false)
                    setStats({
                        range: p.x,
                        maxHeight: p.maxY + activeParams.height,  // total height from ground
                        time: p.time
                    })
                }
            }

            // Draw trajectory trail
            if (trailRef.current.length > 1) {
                ctx.strokeStyle = 'rgba(0, 210, 211, 0.6)'
                ctx.lineWidth = 3
                ctx.beginPath()
                ctx.moveTo(trailRef.current[0].x, trailRef.current[0].y)
                for (let i = 1; i < trailRef.current.length; i++) {
                    ctx.lineTo(trailRef.current[i].x, trailRef.current[i].y)
                }
                ctx.stroke()
            }

            // Draw projectile
            const drawX = LAUNCH_X + p.x * SCALE
            const drawY = GROUND_Y - platformHeight - p.y * SCALE

            // Clamp drawing to screen
            const clampedY = Math.min(drawY, GROUND_Y - 10)

            // Glow
            const gradient = ctx.createRadialGradient(drawX, clampedY, 5, drawX, clampedY, 25)
            gradient.addColorStop(0, '#00d2d3')
            gradient.addColorStop(1, 'transparent')
            ctx.fillStyle = gradient
            ctx.beginPath()
            ctx.arc(drawX, clampedY, 20, 0, Math.PI * 2)
            ctx.fill()

            // Ball
            ctx.fillStyle = '#00d2d3'
            ctx.beginPath()
            ctx.arc(drawX, clampedY, 10, 0, Math.PI * 2)
            ctx.fill()

            // Velocity vector (when not running and not landed)
            if (!isRunning && trailRef.current.length === 0) {
                const angleRad = params.angle * Math.PI / 180
                const arrowLen = params.velocity * 1.2
                const previewPlatformHeight = params.height * SCALE
                const startX = LAUNCH_X
                const startY = GROUND_Y - previewPlatformHeight
                const endX = startX + Math.cos(angleRad) * arrowLen
                const endY = startY - Math.sin(angleRad) * arrowLen

                ctx.strokeStyle = 'rgba(255,255,255,0.8)'
                ctx.lineWidth = 3
                ctx.beginPath()
                ctx.moveTo(startX, startY)
                ctx.lineTo(endX, endY)
                ctx.stroke()

                // Arrow head
                const angle = angleRad
                ctx.beginPath()
                ctx.moveTo(endX, endY)
                ctx.lineTo(endX - 10 * Math.cos(angle - 0.4), endY + 10 * Math.sin(angle - 0.4))
                ctx.moveTo(endX, endY)
                ctx.lineTo(endX - 10 * Math.cos(angle + 0.4), endY + 10 * Math.sin(angle + 0.4))
                ctx.stroke()

                // Preview platform at current slider height
                ctx.fillStyle = 'rgba(85, 85, 85, 0.5)'
                ctx.fillRect(LAUNCH_X - 25, GROUND_Y - previewPlatformHeight, 50, previewPlatformHeight)
            }

            // Display stats
            ctx.fillStyle = 'white'
            ctx.font = '14px Inter, sans-serif'
            const displayStats = (isRunning || trailRef.current.length > 0)
                ? { range: p.x, maxHeight: p.maxY + activeParams.height, time: p.time }
                : stats

            if (trailRef.current.length > 0 || stats.time > 0) {
                ctx.fillText(`Range: ${displayStats.range.toFixed(1)} m`, width - 180, 30)
                ctx.fillText(`Max Height: ${displayStats.maxHeight.toFixed(1)} m`, width - 180, 50)
                ctx.fillText(`Flight Time: ${displayStats.time.toFixed(2)} s`, width - 180, 70)
            }

            animationRef.current = requestAnimationFrame(animate)
        }

        animate()
        return () => cancelAnimationFrame(animationRef.current)
    }, [isRunning, params, stats])

    const launch = () => {
        // Copy current slider params to active params
        activeParamsRef.current = { ...params }

        const angleRad = params.angle * Math.PI / 180
        projectileRef.current = {
            x: 0,
            y: 0,  // starts at platform level (which is params.height above ground)
            vx: params.velocity * Math.cos(angleRad),
            vy: params.velocity * Math.sin(angleRad),
            time: 0,
            maxY: 0,
            landed: false
        }
        trailRef.current = []
        setStats({ range: 0, maxHeight: 0, time: 0 })
        setIsRunning(true)
    }

    const reset = () => {
        setIsRunning(false)
        projectileRef.current = { x: 0, y: 0, vx: 0, vy: 0, time: 0, maxY: 0, landed: false }
        trailRef.current = []
        setStats({ range: 0, maxHeight: 0, time: 0 })
        // Active params will be set on next launch
    }

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
                    <span className="text-green-400">🎯 Projectile Motion</span>
                </h1>
                <div className="w-32" />
            </div>

            <div className="flex gap-4">
                <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="flex-1">
                    <canvas ref={canvasRef} width={1000} height={550} className="w-full rounded-2xl border-2 border-gray-800" />
                </motion.div>

                <motion.div initial={{ x: 30, opacity: 0 }} animate={{ x: 0, opacity: 1 }} className="w-80 space-y-4">
                    <div className="p-5 bg-[#111] rounded-2xl border border-green-900/40">
                        <h3 className="text-green-400 font-bold text-xl mb-4">LAUNCH PARAMETERS</h3>
                        <p className="text-gray-600 text-xs mb-3">Changes apply on next LAUNCH</p>

                        <div className="space-y-4">
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Initial Velocity (v₀)</span>
                                    <span className="text-white font-bold">{params.velocity} m/s</span>
                                </div>
                                <input type="range" min="10" max="80" value={params.velocity}
                                    onChange={(e) => setParams({ ...params, velocity: Number(e.target.value) })}
                                    className="w-full accent-green-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Launch Angle (θ)</span>
                                    <span className="text-white font-bold">{params.angle}°</span>
                                </div>
                                <input type="range" min="5" max="85" value={params.angle}
                                    onChange={(e) => setParams({ ...params, angle: Number(e.target.value) })}
                                    className="w-full accent-green-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Platform Height (h₀)</span>
                                    <span className="text-white font-bold">{params.height} m</span>
                                </div>
                                <input type="range" min="0" max="60" value={params.height}
                                    onChange={(e) => setParams({ ...params, height: Number(e.target.value) })}
                                    className="w-full accent-green-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Gravity (g)</span>
                                    <span className="text-white font-bold">{params.gravity.toFixed(1)} m/s²</span>
                                </div>
                                <input type="range" min="1" max="20" step="0.5" value={params.gravity}
                                    onChange={(e) => setParams({ ...params, gravity: Number(e.target.value) })}
                                    className="w-full accent-green-500"
                                />
                            </div>
                        </div>
                    </div>

                    <div className="p-4 bg-[#111] rounded-2xl border border-gray-800">
                        <h4 className="text-gray-400 text-sm mb-2">KINEMATIC EQUATIONS</h4>
                        <p className="text-xs text-green-400 font-mono">x = v₀·cos(θ)·t</p>
                        <p className="text-xs text-green-400 font-mono">y = h₀ + v₀·sin(θ)·t - ½·g·t²</p>
                    </div>

                    <div className="flex gap-3">
                        <motion.button whileHover={{ scale: 1.02 }} whileTap={{ scale: 0.98 }}
                            onClick={launch} disabled={isRunning}
                            className={`flex-1 py-4 rounded-xl font-bold text-lg ${isRunning ? 'bg-gray-800 text-gray-500' : 'bg-gradient-to-r from-green-500 to-emerald-600 text-white'}`}
                        >
                            🚀 LAUNCH
                        </motion.button>
                        <motion.button whileHover={{ scale: 1.02 }} whileTap={{ scale: 0.98 }}
                            onClick={reset}
                            className="flex-1 py-4 bg-[#111] border-2 border-gray-700 rounded-xl font-bold text-lg text-gray-300 hover:text-white"
                        >
                            ↻ RESET
                        </motion.button>
                    </div>
                </motion.div>
            </div>
        </div>
    )
}

export default ProjectileSim
