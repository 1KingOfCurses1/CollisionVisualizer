import { motion } from 'framer-motion'

const Instructions = ({ onBack }) => {
    return (
        <div className="min-h-screen flex flex-col items-center justify-center p-8">
            <div className="fixed top-8 left-8">
                <motion.button
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                    onClick={onBack}
                    className="px-6 py-2 bg-[#111] border border-gray-800 rounded-lg text-gray-400 hover:text-white hover:border-cyan-500/50 transition-all"
                >
                    ← BACK
                </motion.button>
            </div>

            <motion.h1
                initial={{ y: -30, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                className="text-4xl font-bold text-cyan-400 mb-8"
            >
                SPH4U LAB MANUAL
            </motion.h1>

            <motion.div
                initial={{ y: 20, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                transition={{ delay: 0.2 }}
                className="w-full max-w-4xl bg-[#111] rounded-2xl border border-gray-800 p-8 max-h-[70vh] overflow-y-auto"
            >
                <div className="space-y-8">
                    <section>
                        <h2 className="text-2xl font-bold text-red-400 mb-4 flex items-center gap-3">
                            💥 Momentum & Collisions
                        </h2>
                        <p className="text-gray-400 mb-3">Simulate elastic and inelastic collisions between two bodies.</p>
                        <ul className="text-gray-500 space-y-1 text-sm">
                            <li>• Adjust mass for each body (applies on RESET)</li>
                            <li>• Control X and Y velocity to set direction</li>
                            <li>• Elasticity: 100% = perfect elastic, 0% = inelastic</li>
                            <li>• Observe momentum and energy conservation</li>
                        </ul>
                    </section>

                    <section>
                        <h2 className="text-2xl font-bold text-green-400 mb-4 flex items-center gap-3">
                            🎯 Projectile Motion
                        </h2>
                        <p className="text-gray-400 mb-3">Launch projectiles and analyze trajectories.</p>
                        <ul className="text-gray-500 space-y-1 text-sm">
                            <li>• Set initial velocity and launch angle</li>
                            <li>• Adjust launch height and gravity</li>
                            <li>• View real-time range and max height</li>
                            <li>• Key equations: x = v₀cos(θ)t, y = v₀sin(θ)t - ½gt²</li>
                        </ul>
                    </section>

                    <section>
                        <h2 className="text-2xl font-bold text-purple-400 mb-4 flex items-center gap-3">
                            🪐 Gravitational Fields
                        </h2>
                        <p className="text-gray-400 mb-3">Explore orbital mechanics and Newton's Law of Gravitation.</p>
                        <ul className="text-gray-500 space-y-1 text-sm">
                            <li>• Adjust sun and planet masses</li>
                            <li>• Set initial orbital radius and speed</li>
                            <li>• Observe elliptical orbits and escape velocities</li>
                            <li>• Key formula: F = Gm₁m₂/r²</li>
                        </ul>
                    </section>

                    <section>
                        <h2 className="text-2xl font-bold text-yellow-400 mb-4 flex items-center gap-3">
                            ⚡ Electric Fields
                        </h2>
                        <p className="text-gray-400 mb-3">Visualize electric field vectors from point charges.</p>
                        <ul className="text-gray-500 space-y-1 text-sm">
                            <li>• Add positive (+) and negative (−) charges</li>
                            <li>• Drag charges to reposition them</li>
                            <li>• Adjust charge strength</li>
                            <li>• Key formula: F = kq₁q₂/r² (Coulomb's Law)</li>
                        </ul>
                    </section>

                    <section>
                        <h2 className="text-2xl font-bold text-cyan-400 mb-4 flex items-center gap-3">
                            🌊 Wave Interference
                        </h2>
                        <p className="text-gray-400 mb-3">Simulate Young's double-slit experiment.</p>
                        <ul className="text-gray-500 space-y-1 text-sm">
                            <li>• Adjust wavelength, slit separation, and width</li>
                            <li>• Observe interference pattern on screen</li>
                            <li>• Bright fringes: d·sin(θ) = nλ</li>
                            <li>• Dark fringes: d·sin(θ) = (n+½)λ</li>
                        </ul>
                    </section>

                    <section className="border-t border-gray-800 pt-6">
                        <h2 className="text-xl font-bold text-gray-300 mb-4">SPH4U Curriculum Alignment</h2>
                        <div className="grid grid-cols-2 gap-4 text-sm">
                            <div className="p-3 bg-gray-900 rounded-lg">
                                <span className="text-cyan-400 font-bold">Unit 1:</span>
                                <span className="text-gray-400 ml-2">Dynamics</span>
                            </div>
                            <div className="p-3 bg-gray-900 rounded-lg">
                                <span className="text-cyan-400 font-bold">Unit 2:</span>
                                <span className="text-gray-400 ml-2">Energy & Momentum</span>
                            </div>
                            <div className="p-3 bg-gray-900 rounded-lg">
                                <span className="text-cyan-400 font-bold">Unit 3:</span>
                                <span className="text-gray-400 ml-2">Gravitational, Electric & Magnetic Fields</span>
                            </div>
                            <div className="p-3 bg-gray-900 rounded-lg">
                                <span className="text-cyan-400 font-bold">Unit 4:</span>
                                <span className="text-gray-400 ml-2">The Wave Nature of Light</span>
                            </div>
                        </div>
                    </section>
                </div>
            </motion.div>
        </div>
    )
}

export default Instructions
