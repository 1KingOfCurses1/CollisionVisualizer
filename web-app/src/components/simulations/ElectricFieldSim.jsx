import { useRef, useEffect, useState, useCallback } from 'react'
import { motion } from 'framer-motion'

const ElectricFieldSim = ({ onBack }) => {
    const canvasRef = useRef(null)
    const [charges, setCharges] = useState([
        { x: 350, y: 300, q: 2, id: 1 },
        { x: 650, y: 300, q: -2, id: 2 }
    ])
    const [draggedCharge, setDraggedCharge] = useState(null)
    const [showField, setShowField] = useState(true)
    const [showLines, setShowLines] = useState(true)

    // Coulomb's constant (scaled)
    const k = 1000

    const draw = useCallback(() => {
        const canvas = canvasRef.current
        if (!canvas) return
        const ctx = canvas.getContext('2d')
        const width = canvas.width
        const height = canvas.height

        // Clear
        ctx.fillStyle = '#0a0a0a'
        ctx.fillRect(0, 0, width, height)

        // Calculate and draw electric field
        if (showField) {
            const gridSize = 30
            for (let x = gridSize; x < width; x += gridSize) {
                for (let y = gridSize; y < height; y += gridSize) {
                    let Ex = 0, Ey = 0

                    // Sum electric field from all charges: E = kq/r²
                    for (const charge of charges) {
                        const dx = x - charge.x
                        const dy = y - charge.y
                        const rSq = dx * dx + dy * dy
                        const r = Math.sqrt(rSq)

                        if (r < 40) continue  // Skip if too close to charge

                        // Electric field magnitude: E = kq/r²
                        // Direction: away from positive, toward negative
                        const E = k * charge.q / rSq
                        Ex += E * (dx / r)
                        Ey += E * (dy / r)
                    }

                    const mag = Math.sqrt(Ex * Ex + Ey * Ey)
                    if (mag > 0.1) {
                        // Normalize and scale arrow
                        const arrowLen = Math.min(12, mag * 0.5)
                        const endX = x + (Ex / mag) * arrowLen
                        const endY = y + (Ey / mag) * arrowLen

                        // Color based on magnitude
                        const intensity = Math.min(1, mag / 50)
                        ctx.strokeStyle = `rgba(0, 210, 211, ${0.3 + intensity * 0.5})`
                        ctx.lineWidth = 1 + intensity
                        ctx.beginPath()
                        ctx.moveTo(x, y)
                        ctx.lineTo(endX, endY)
                        ctx.stroke()

                        // Arrow head
                        if (arrowLen > 5) {
                            const angle = Math.atan2(Ey, Ex)
                            ctx.beginPath()
                            ctx.moveTo(endX, endY)
                            ctx.lineTo(endX - 4 * Math.cos(angle - 0.5), endY - 4 * Math.sin(angle - 0.5))
                            ctx.lineTo(endX - 4 * Math.cos(angle + 0.5), endY - 4 * Math.sin(angle + 0.5))
                            ctx.closePath()
                            ctx.fillStyle = ctx.strokeStyle
                            ctx.fill()
                        }
                    }
                }
            }
        }

        // Draw field lines (from positive charges)
        if (showLines) {
            for (const charge of charges) {
                if (charge.q > 0) {
                    // Draw field lines emanating from positive charges
                    const numLines = Math.abs(charge.q) * 6
                    for (let i = 0; i < numLines; i++) {
                        const startAngle = (i / numLines) * Math.PI * 2
                        let lx = charge.x + Math.cos(startAngle) * 35
                        let ly = charge.y + Math.sin(startAngle) * 35

                        ctx.strokeStyle = 'rgba(255, 200, 100, 0.4)'
                        ctx.lineWidth = 1.5
                        ctx.beginPath()
                        ctx.moveTo(lx, ly)

                        // Trace field line
                        for (let step = 0; step < 150; step++) {
                            let Ex = 0, Ey = 0

                            for (const c of charges) {
                                const dx = lx - c.x
                                const dy = ly - c.y
                                const rSq = dx * dx + dy * dy
                                const r = Math.sqrt(rSq)
                                if (r < 25) {
                                    // Reached a charge, stop
                                    step = 200
                                    break
                                }
                                const E = k * c.q / rSq
                                Ex += E * (dx / r)
                                Ey += E * (dy / r)
                            }

                            const mag = Math.sqrt(Ex * Ex + Ey * Ey)
                            if (mag < 0.01) break

                            // Move along field direction
                            lx += (Ex / mag) * 5
                            ly += (Ey / mag) * 5

                            // Stop if out of bounds
                            if (lx < 0 || lx > width || ly < 0 || ly > height) break

                            ctx.lineTo(lx, ly)
                        }
                        ctx.stroke()
                    }
                }
            }
        }

        // Draw charges
        for (const charge of charges) {
            const color = charge.q > 0 ? '#ff4757' : '#1e90ff'
            const radius = 25 + Math.abs(charge.q) * 3

            // Glow
            const glow = ctx.createRadialGradient(charge.x, charge.y, radius * 0.5,
                charge.x, charge.y, radius * 2)
            glow.addColorStop(0, color)
            glow.addColorStop(1, 'transparent')
            ctx.fillStyle = glow
            ctx.beginPath()
            ctx.arc(charge.x, charge.y, radius * 2, 0, Math.PI * 2)
            ctx.fill()

            // Charge body
            ctx.fillStyle = color
            ctx.beginPath()
            ctx.arc(charge.x, charge.y, radius, 0, Math.PI * 2)
            ctx.fill()

            // Symbol
            ctx.fillStyle = 'white'
            ctx.font = 'bold 28px Inter'
            ctx.textAlign = 'center'
            ctx.textBaseline = 'middle'
            ctx.fillText(charge.q > 0 ? '+' : '−', charge.x, charge.y)
        }

        // Instructions
        ctx.fillStyle = 'rgba(255,255,255,0.5)'
        ctx.font = '12px Inter'
        ctx.textAlign = 'left'
        ctx.fillText('Drag charges to move them', 20, height - 20)
    }, [charges, showField, showLines, k])

    useEffect(() => {
        draw()
    }, [draw])

    const handleMouseDown = (e) => {
        const rect = canvasRef.current.getBoundingClientRect()
        const x = (e.clientX - rect.left) * (1000 / rect.width)
        const y = (e.clientY - rect.top) * (600 / rect.height)

        for (const charge of charges) {
            const dist = Math.sqrt((x - charge.x) ** 2 + (y - charge.y) ** 2)
            if (dist < 35) {
                setDraggedCharge(charge.id)
                return
            }
        }
    }

    const handleMouseMove = (e) => {
        if (draggedCharge === null) return
        const rect = canvasRef.current.getBoundingClientRect()
        const x = Math.max(30, Math.min(970, (e.clientX - rect.left) * (1000 / rect.width)))
        const y = Math.max(30, Math.min(570, (e.clientY - rect.top) * (600 / rect.height)))

        setCharges(prev => prev.map(c =>
            c.id === draggedCharge ? { ...c, x, y } : c
        ))
    }

    const handleMouseUp = () => setDraggedCharge(null)

    const addCharge = (positive) => {
        const newId = Math.max(...charges.map(c => c.id), 0) + 1
        setCharges([...charges, {
            x: 500 + (Math.random() - 0.5) * 200,
            y: 300 + (Math.random() - 0.5) * 200,
            q: positive ? 1 : -1,
            id: newId
        }])
    }

    const updateCharge = (id, delta) => {
        setCharges(prev => prev.map(c => {
            if (c.id !== id) return c
            const newQ = c.q + delta
            // Don't allow zero charge, and limit range
            if (newQ === 0) return c
            if (Math.abs(newQ) > 5) return c
            return { ...c, q: newQ }
        }))
    }

    const removeCharge = (id) => {
        if (charges.length > 1) {
            setCharges(charges.filter(c => c.id !== id))
        }
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
                    <span className="text-yellow-400">⚡ Electric Fields</span>
                </h1>
                <div className="w-32" />
            </div>

            <div className="flex gap-4">
                <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="flex-1">
                    <canvas
                        ref={canvasRef}
                        width={1000}
                        height={600}
                        className="w-full rounded-2xl border-2 border-gray-800 cursor-pointer"
                        onMouseDown={handleMouseDown}
                        onMouseMove={handleMouseMove}
                        onMouseUp={handleMouseUp}
                        onMouseLeave={handleMouseUp}
                    />
                </motion.div>

                <motion.div initial={{ x: 30, opacity: 0 }} animate={{ x: 0, opacity: 1 }} className="w-80 space-y-4">
                    <div className="p-5 bg-[#111] rounded-2xl border border-yellow-900/40">
                        <h3 className="text-yellow-400 font-bold text-xl mb-4">CHARGE CONTROLS</h3>

                        <div className="flex gap-2 mb-4">
                            <button onClick={() => addCharge(true)}
                                className="flex-1 py-3 bg-red-900/30 border border-red-700 rounded-lg text-red-400 font-bold hover:bg-red-900/50"
                            >
                                + Positive
                            </button>
                            <button onClick={() => addCharge(false)}
                                className="flex-1 py-3 bg-blue-900/30 border border-blue-700 rounded-lg text-blue-400 font-bold hover:bg-blue-900/50"
                            >
                                + Negative
                            </button>
                        </div>

                        <div className="space-y-2 max-h-40 overflow-y-auto">
                            {charges.map((c, i) => (
                                <div key={c.id} className="flex items-center justify-between p-2 bg-gray-900 rounded-lg">
                                    <span className={`font-bold ${c.q > 0 ? 'text-red-400' : 'text-blue-400'}`}>
                                        Q{i + 1}: {c.q > 0 ? '+' : ''}{c.q}
                                    </span>
                                    <div className="flex gap-1">
                                        <button onClick={() => updateCharge(c.id, -1)}
                                            className="px-3 py-1 bg-gray-800 rounded text-gray-400 hover:text-white">−</button>
                                        <button onClick={() => updateCharge(c.id, 1)}
                                            className="px-3 py-1 bg-gray-800 rounded text-gray-400 hover:text-white">+</button>
                                        <button onClick={() => removeCharge(c.id)}
                                            className="px-3 py-1 bg-red-900/50 rounded text-red-400 hover:text-red-300">✕</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>

                    <div className="p-4 bg-[#111] rounded-2xl border border-gray-800 space-y-2">
                        <label className="flex items-center gap-3 cursor-pointer">
                            <input type="checkbox" checked={showField} onChange={(e) => setShowField(e.target.checked)}
                                className="w-5 h-5 accent-cyan-500" />
                            <span className="text-gray-300">Show Field Vectors</span>
                        </label>
                        <label className="flex items-center gap-3 cursor-pointer">
                            <input type="checkbox" checked={showLines} onChange={(e) => setShowLines(e.target.checked)}
                                className="w-5 h-5 accent-yellow-500" />
                            <span className="text-gray-300">Show Field Lines</span>
                        </label>
                    </div>

                    <div className="p-4 bg-[#111] rounded-2xl border border-gray-800">
                        <h4 className="text-gray-400 text-sm mb-2">COULOMB'S LAW</h4>
                        <p className="text-lg text-yellow-300 font-mono">E = k·q / r²</p>
                        <p className="text-xs text-gray-500 mt-2">
                            Field points away from + charges and toward − charges.
                        </p>
                    </div>
                </motion.div>
            </div>
        </div>
    )
}

export default ElectricFieldSim
