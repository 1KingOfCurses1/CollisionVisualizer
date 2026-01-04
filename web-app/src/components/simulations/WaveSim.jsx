import { useRef, useEffect, useState, useCallback } from 'react'
import { motion } from 'framer-motion'

const WaveSim = ({ onBack }) => {
    const canvasRef = useRef(null)
    const [time, setTime] = useState(0)
    const [isRunning, setIsRunning] = useState(true)

    const [params, setParams] = useState({
        wavelength: 40,
        slitSeparation: 120,
        slitWidth: 15,
        screenDistance: 500
    })

    // Animation loop
    useEffect(() => {
        let animationId
        let lastTime = 0

        const animate = (currentTime) => {
            if (isRunning) {
                if (currentTime - lastTime > 30) {
                    setTime(t => t + 0.1)
                    lastTime = currentTime
                }
            }
            animationId = requestAnimationFrame(animate)
        }

        animationId = requestAnimationFrame(animate)
        return () => cancelAnimationFrame(animationId)
    }, [isRunning])

    // Draw function
    const draw = useCallback(() => {
        const canvas = canvasRef.current
        if (!canvas) return
        const ctx = canvas.getContext('2d')
        const width = canvas.width
        const height = canvas.height

        // Clear
        ctx.fillStyle = '#0a0a0a'
        ctx.fillRect(0, 0, width, height)

        const centerY = height / 2
        const slitX = 180
        const screenX = slitX + params.screenDistance
        const slit1Y = centerY - params.slitSeparation / 2
        const slit2Y = centerY + params.slitSeparation / 2
        const slitHalfWidth = params.slitWidth / 2

        // Draw barrier with slits
        ctx.fillStyle = '#333'
        ctx.fillRect(slitX - 8, 0, 16, slit1Y - slitHalfWidth)
        ctx.fillRect(slitX - 8, slit1Y + slitHalfWidth, 16, slit2Y - slit1Y - params.slitWidth)
        ctx.fillRect(slitX - 8, slit2Y + slitHalfWidth, 16, height - slit2Y - slitHalfWidth)

        // Draw wave source
        ctx.fillStyle = '#00d2d3'
        ctx.beginPath()
        ctx.arc(50, centerY, 12, 0, Math.PI * 2)
        ctx.fill()
        ctx.fillStyle = 'white'
        ctx.font = '10px Inter'
        ctx.textAlign = 'center'
        ctx.fillText('Source', 50, centerY + 25)

        // Draw incoming plane waves
        ctx.strokeStyle = 'rgba(0, 210, 211, 0.3)'
        ctx.lineWidth = 2
        for (let x = 20; x < slitX - 10; x += params.wavelength) {
            const waveX = x + ((time * 20) % params.wavelength)
            if (waveX < slitX - 10 && waveX > 20) {
                ctx.beginPath()
                ctx.moveTo(waveX, centerY - 150)
                ctx.lineTo(waveX, centerY + 150)
                ctx.stroke()
            }
        }

        // Draw circular waves from slits
        const maxRadius = params.screenDistance + 100
        ctx.lineWidth = 1.5

        // Wave from slit 1
        for (let r = 0; r < maxRadius; r += params.wavelength) {
            const phase = (time * 20) % params.wavelength
            const radius = r + phase
            if (radius > 0 && radius < maxRadius) {
                const alpha = 0.4 * (1 - radius / maxRadius)
                ctx.strokeStyle = `rgba(0, 210, 211, ${alpha})`
                ctx.beginPath()
                ctx.arc(slitX, slit1Y, radius, -Math.PI / 2.5, Math.PI / 2.5)
                ctx.stroke()
            }
        }

        // Wave from slit 2
        for (let r = 0; r < maxRadius; r += params.wavelength) {
            const phase = (time * 20) % params.wavelength
            const radius = r + phase
            if (radius > 0 && radius < maxRadius) {
                const alpha = 0.4 * (1 - radius / maxRadius)
                ctx.strokeStyle = `rgba(0, 150, 255, ${alpha})`
                ctx.beginPath()
                ctx.arc(slitX, slit2Y, radius, -Math.PI / 2.5, Math.PI / 2.5)
                ctx.stroke()
            }
        }

        // Draw detection screen
        ctx.fillStyle = '#222'
        ctx.fillRect(screenX, 0, 40, height)

        // Calculate and draw interference pattern
        for (let y = 0; y < height; y += 2) {
            // Path lengths from each slit
            const r1 = Math.sqrt(params.screenDistance ** 2 + (y - slit1Y) ** 2)
            const r2 = Math.sqrt(params.screenDistance ** 2 + (y - slit2Y) ** 2)
            const pathDiff = r2 - r1

            // Phase difference = 2π × (path difference / wavelength)
            const phaseDiff = (2 * Math.PI * pathDiff) / params.wavelength

            // Interference: I = I0 × cos²(Δφ/2)
            // Two waves: A1 = cos(phase1), A2 = cos(phase2)
            // Combined amplitude squared
            const intensity = Math.cos(phaseDiff / 2) ** 2

            // Single-slit diffraction envelope
            const theta = Math.atan2(y - centerY, params.screenDistance)
            const beta = (Math.PI * params.slitWidth * Math.sin(theta)) / params.wavelength
            const envelope = beta === 0 ? 1 : (Math.sin(beta) / beta) ** 2

            const finalIntensity = intensity * envelope

            // Draw with oscillating intensity for animation
            const animatedIntensity = finalIntensity * (0.7 + 0.3 * Math.sin(time * 2))
            ctx.fillStyle = `rgba(0, 210, 211, ${animatedIntensity * 0.9})`
            ctx.fillRect(screenX + 5, y, 30, 2)
        }

        // Labels
        ctx.fillStyle = '#666'
        ctx.font = '11px Inter'
        ctx.textAlign = 'center'
        ctx.fillText('Barrier', slitX, 15)
        ctx.fillText('Screen', screenX + 20, 15)
        ctx.fillText(`d = ${params.slitSeparation}`, slitX, height - 10)

        // Draw slit markers
        ctx.fillStyle = '#00d2d3'
        ctx.beginPath()
        ctx.arc(slitX, slit1Y, 4, 0, Math.PI * 2)
        ctx.fill()
        ctx.beginPath()
        ctx.arc(slitX, slit2Y, 4, 0, Math.PI * 2)
        ctx.fill()

    }, [time, params])

    useEffect(() => {
        draw()
    }, [draw])

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
                    <span className="text-cyan-400">🌊 Wave Interference</span>
                </h1>
                <div className="w-32" />
            </div>

            <div className="flex gap-4">
                <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="flex-1">
                    <canvas ref={canvasRef} width={1000} height={600} className="w-full rounded-2xl border-2 border-gray-800" />
                </motion.div>

                <motion.div initial={{ x: 30, opacity: 0 }} animate={{ x: 0, opacity: 1 }} className="w-80 space-y-4">
                    <div className="p-5 bg-[#111] rounded-2xl border border-cyan-900/40">
                        <h3 className="text-cyan-400 font-bold text-xl mb-4">WAVE PARAMETERS</h3>

                        <div className="space-y-4">
                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Wavelength (λ)</span>
                                    <span className="text-white font-bold">{params.wavelength} px</span>
                                </div>
                                <input type="range" min="20" max="80" value={params.wavelength}
                                    onChange={(e) => setParams({ ...params, wavelength: Number(e.target.value) })}
                                    className="w-full accent-cyan-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Slit Separation (d)</span>
                                    <span className="text-white font-bold">{params.slitSeparation} px</span>
                                </div>
                                <input type="range" min="60" max="250" value={params.slitSeparation}
                                    onChange={(e) => setParams({ ...params, slitSeparation: Number(e.target.value) })}
                                    className="w-full accent-cyan-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Slit Width (a)</span>
                                    <span className="text-white font-bold">{params.slitWidth} px</span>
                                </div>
                                <input type="range" min="5" max="50" value={params.slitWidth}
                                    onChange={(e) => setParams({ ...params, slitWidth: Number(e.target.value) })}
                                    className="w-full accent-cyan-500"
                                />
                            </div>

                            <div>
                                <div className="flex justify-between text-sm mb-1">
                                    <span className="text-gray-400">Screen Distance (L)</span>
                                    <span className="text-white font-bold">{params.screenDistance} px</span>
                                </div>
                                <input type="range" min="300" max="700" value={params.screenDistance}
                                    onChange={(e) => setParams({ ...params, screenDistance: Number(e.target.value) })}
                                    className="w-full accent-cyan-500"
                                />
                            </div>
                        </div>
                    </div>

                    <div className="p-4 bg-[#111] rounded-2xl border border-gray-800">
                        <h4 className="text-gray-400 text-sm mb-2">INTERFERENCE CONDITIONS</h4>
                        <p className="text-sm text-cyan-400 font-mono">Maxima: d·sin(θ) = nλ</p>
                        <p className="text-sm text-cyan-400 font-mono">Minima: d·sin(θ) = (n+½)λ</p>
                        <p className="text-xs text-gray-500 mt-2">n = 0, ±1, ±2, ... (order number)</p>
                    </div>

                    <div className="flex gap-3">
                        <motion.button whileHover={{ scale: 1.02 }} whileTap={{ scale: 0.98 }}
                            onClick={() => setIsRunning(!isRunning)}
                            className={`flex-1 py-4 rounded-xl font-bold text-lg ${isRunning ? 'bg-orange-600 text-white' : 'bg-gradient-to-r from-cyan-500 to-blue-600 text-white'}`}
                        >
                            {isRunning ? '⏸ PAUSE' : '▶ START'}
                        </motion.button>
                    </div>

                    <div className="p-4 bg-gradient-to-r from-cyan-900/20 to-blue-900/20 rounded-2xl border border-cyan-800/30">
                        <h4 className="text-cyan-300 font-bold text-sm mb-2">Young's Double-Slit</h4>
                        <p className="text-cyan-400/70 text-xs">
                            Coherent waves pass through two slits and interfere. Bright fringes occur where waves are in phase (constructive), dark fringes where out of phase (destructive).
                        </p>
                    </div>
                </motion.div>
            </div>
        </div>
    )
}

export default WaveSim
