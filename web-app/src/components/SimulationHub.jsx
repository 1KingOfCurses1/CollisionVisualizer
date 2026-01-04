import { motion } from 'framer-motion'

const simulations = [
    {
        id: 'collision',
        title: 'Momentum & Collisions',
        unit: 'Unit 1: Dynamics',
        icon: '💥',
        color: 'from-red-500 to-orange-500',
        desc: 'Elastic and inelastic collisions, conservation of momentum'
    },
    {
        id: 'projectile',
        title: 'Projectile Motion',
        unit: 'Unit 1: Dynamics',
        icon: '🎯',
        color: 'from-green-500 to-emerald-500',
        desc: 'Launch angles, trajectories, range calculations'
    },
    {
        id: 'gravity',
        title: 'Gravitational Fields',
        unit: 'Unit 2: Fields',
        icon: '🪐',
        color: 'from-purple-500 to-pink-500',
        desc: 'Orbital mechanics, gravitational potential energy'
    },
    {
        id: 'electric',
        title: 'Electric Fields',
        unit: 'Unit 2: Fields',
        icon: '⚡',
        color: 'from-yellow-500 to-amber-500',
        desc: 'Point charges, field lines, Coulomb\'s law'
    },
    {
        id: 'wave',
        title: 'Wave Interference',
        unit: 'Unit 3: Light & Waves',
        icon: '🌊',
        color: 'from-cyan-500 to-blue-500',
        desc: 'Double-slit experiment, interference patterns'
    },
]

const SimulationHub = ({ onBack, onSelect }) => {
    return (
        <div className="min-h-screen p-8">
            {/* Header */}
            <div className="flex items-center justify-between mb-8">
                <motion.button
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                    onClick={onBack}
                    className="px-6 py-3 bg-[#111] border border-gray-800 rounded-xl text-gray-400 hover:text-white hover:border-cyan-500/50 transition-all font-semibold"
                >
                    ← MAIN MENU
                </motion.button>
                <h1 className="text-4xl font-bold text-white">
                    <span className="text-cyan-400">SPH4U</span> Simulations
                </h1>
                <div className="w-40" />
            </div>

            {/* Simulation Grid */}
            <div className="max-w-6xl mx-auto grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {simulations.map((sim, i) => (
                    <motion.button
                        key={sim.id}
                        initial={{ y: 30, opacity: 0 }}
                        animate={{ y: 0, opacity: 1 }}
                        transition={{ delay: i * 0.1 }}
                        whileHover={{ scale: 1.02, y: -5 }}
                        whileTap={{ scale: 0.98 }}
                        onClick={() => onSelect(sim.id)}
                        className="group relative p-6 bg-[#111] border border-gray-800 rounded-2xl text-left overflow-hidden transition-all duration-300 hover:border-gray-600"
                    >
                        {/* Gradient overlay */}
                        <div className={`absolute inset-0 bg-gradient-to-br ${sim.color} opacity-0 group-hover:opacity-10 transition-opacity`} />

                        <div className="relative z-10">
                            <div className="flex items-start justify-between mb-4">
                                <span className="text-4xl">{sim.icon}</span>
                                <span className="text-xs text-gray-600 bg-gray-900 px-2 py-1 rounded">{sim.unit}</span>
                            </div>
                            <h3 className="text-xl font-bold text-white mb-2">{sim.title}</h3>
                            <p className="text-gray-500 text-sm">{sim.desc}</p>

                            <div className={`mt-4 inline-flex items-center gap-2 text-sm font-semibold bg-gradient-to-r ${sim.color} bg-clip-text text-transparent`}>
                                Launch Simulation →
                            </div>
                        </div>
                    </motion.button>
                ))}
            </div>

            {/* Curriculum Note */}
            <motion.p
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 0.6 }}
                className="text-center text-gray-600 mt-12"
            >
                Based on the Ontario SPH4U Curriculum • Grade 12 Physics
            </motion.p>
        </div>
    )
}

export default SimulationHub
