import { useState } from 'react'
import { motion } from 'framer-motion'

const terms = [
    // Unit 1: Dynamics
    { name: 'Momentum', definition: 'The product of mass and velocity (p = mv). A vector quantity conserved in closed systems.', unit: 'Dynamics' },
    { name: 'Impulse', definition: 'Change in momentum (J = Δp = FΔt). The area under a force-time graph.', unit: 'Dynamics' },
    { name: 'Newton\'s Third Law', definition: 'For every action force, there is an equal and opposite reaction force.', unit: 'Dynamics' },

    // Unit 2: Energy & Momentum
    { name: 'Kinetic Energy', definition: 'Energy of motion (KE = ½mv²). A scalar quantity measured in Joules.', unit: 'Energy' },
    { name: 'Elastic Collision', definition: 'A collision where both momentum AND kinetic energy are conserved.', unit: 'Energy' },
    { name: 'Inelastic Collision', definition: 'A collision where momentum is conserved but kinetic energy is not (some is converted to heat/sound).', unit: 'Energy' },
    { name: 'Coefficient of Restitution', definition: 'Ratio of relative velocities after and before collision (e = v₂-v₁)/(u₁-u₂). e=1 for elastic, e=0 for perfectly inelastic.', unit: 'Energy' },

    // Unit 3: Fields
    { name: 'Gravitational Field', definition: 'Region of space where a mass experiences a gravitational force. g = Fg/m = GM/r²', unit: 'Fields' },
    { name: 'Electric Field', definition: 'Region of space where a charge experiences an electric force. E = Fe/q = kQ/r²', unit: 'Fields' },
    { name: 'Coulomb\'s Law', definition: 'F = kq₁q₂/r². The electric force between point charges is proportional to product of charges and inversely proportional to distance squared.', unit: 'Fields' },
    { name: 'Newton\'s Law of Gravitation', definition: 'F = Gm₁m₂/r². Gravitational force between masses is proportional to product of masses and inversely proportional to distance squared.', unit: 'Fields' },
    { name: 'Orbital Velocity', definition: 'Speed needed for circular orbit: v = √(GM/r). Independent of satellite mass.', unit: 'Fields' },
    { name: 'Escape Velocity', definition: 'Minimum velocity to escape gravitational field: v = √(2GM/r).', unit: 'Fields' },

    // Unit 4: Waves & Light
    { name: 'Wavelength', definition: 'Distance between consecutive crests (λ). Related to frequency by v = fλ.', unit: 'Waves' },
    { name: 'Interference', definition: 'Superposition of waves. Constructive: waves in phase. Destructive: waves out of phase.', unit: 'Waves' },
    { name: 'Double-Slit Equation', definition: 'd·sin(θ) = nλ for bright fringes (constructive), d·sin(θ) = (n+½)λ for dark fringes (destructive).', unit: 'Waves' },
    { name: 'Diffraction', definition: 'Bending of waves around obstacles or through openings. More pronounced when opening ≈ wavelength.', unit: 'Waves' },

    // Unit 5: Modern Physics
    { name: 'Photoelectric Effect', definition: 'Emission of electrons when light hits a material. KEmax = hf - W (work function). Proves light has particle nature.', unit: 'Modern' },
    { name: 'Photon Energy', definition: 'E = hf = hc/λ. Each photon has discrete energy proportional to frequency.', unit: 'Modern' },
    { name: 'de Broglie Wavelength', definition: 'λ = h/p = h/(mv). All matter has wave properties; significant for small masses.', unit: 'Modern' },
]

const Dictionary = ({ onBack }) => {
    const [search, setSearch] = useState('')
    const [selected, setSelected] = useState(null)
    const [filterUnit, setFilterUnit] = useState('All')

    const units = ['All', 'Dynamics', 'Energy', 'Fields', 'Waves', 'Modern']

    const filtered = terms.filter(t =>
        t.name.toLowerCase().includes(search.toLowerCase()) &&
        (filterUnit === 'All' || t.unit === filterUnit)
    )

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
                className="text-4xl font-bold text-cyan-400 mb-6"
            >
                SPH4U PHYSICS INDEX
            </motion.h1>

            <div className="w-full max-w-3xl">
                {/* Search and Filter */}
                <motion.div
                    initial={{ y: 20, opacity: 0 }}
                    animate={{ y: 0, opacity: 1 }}
                    className="mb-4 flex gap-3"
                >
                    <input
                        type="text"
                        placeholder="Search terminology..."
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                        className="flex-1 px-5 py-3 bg-[#111] border border-gray-800 rounded-xl text-white placeholder-gray-600 focus:border-cyan-500 focus:outline-none transition-all"
                    />
                    <select
                        value={filterUnit}
                        onChange={(e) => setFilterUnit(e.target.value)}
                        className="px-4 py-3 bg-[#111] border border-gray-800 rounded-xl text-white focus:border-cyan-500 focus:outline-none"
                    >
                        {units.map(u => (
                            <option key={u} value={u}>{u === 'All' ? 'All Units' : `Unit: ${u}`}</option>
                        ))}
                    </select>
                </motion.div>

                {/* Results */}
                <div className="grid gap-2 max-h-[55vh] overflow-y-auto pr-2">
                    {filtered.map((term, i) => (
                        <motion.div
                            key={term.name}
                            initial={{ x: -20, opacity: 0 }}
                            animate={{ x: 0, opacity: 1 }}
                            transition={{ delay: i * 0.03 }}
                            onClick={() => setSelected(selected === term.name ? null : term.name)}
                            className={`p-4 bg-[#111] rounded-xl border cursor-pointer transition-all ${selected === term.name
                                    ? 'border-cyan-500 bg-cyan-500/5'
                                    : 'border-gray-800 hover:border-gray-700'
                                }`}
                        >
                            <div className="flex items-center justify-between">
                                <div className="flex items-center gap-3">
                                    <span className="text-xs px-2 py-1 rounded bg-gray-900 text-gray-500">{term.unit}</span>
                                    <h3 className="text-white font-semibold">{term.name}</h3>
                                </div>
                                <span className="text-gray-600">{selected === term.name ? '−' : '+'}</span>
                            </div>
                            {selected === term.name && (
                                <motion.p
                                    initial={{ height: 0, opacity: 0 }}
                                    animate={{ height: 'auto', opacity: 1 }}
                                    className="mt-3 text-gray-400 text-sm pl-16"
                                >
                                    {term.definition}
                                </motion.p>
                            )}
                        </motion.div>
                    ))}
                </div>

                {filtered.length === 0 && (
                    <p className="text-center text-gray-600 py-8">No terms found</p>
                )}

                <p className="text-center text-gray-700 text-sm mt-4">
                    {terms.length} terms covering the full SPH4U curriculum
                </p>
            </div>
        </div>
    )
}

export default Dictionary
