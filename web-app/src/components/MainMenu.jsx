import { motion } from 'framer-motion'

const MainMenu = ({ onNavigate }) => {
    const menuItems = [
        { id: 'simulations', label: 'PHYSICS SIMULATIONS', icon: '⚛️', desc: 'Interactive SPH4U Labs' },
        { id: 'instructions', label: 'INSTRUCTIONS', icon: '📖', desc: 'How to use the system' },
        { id: 'dictionary', label: 'PHYSICS DICTIONARY', icon: '🔍', desc: 'Key terms and definitions' },
        { id: 'credits', label: 'PROJECT CREDITS', icon: '👥', desc: 'Development team' },
    ]

    return (
        <div className="min-h-screen flex flex-col items-center justify-center p-8">
            {/* Title */}
            <motion.div
                initial={{ y: -50, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                transition={{ duration: 0.6 }}
                className="text-center mb-12"
            >
                <h1 className="text-7xl font-bold bg-gradient-to-r from-cyan-400 via-blue-500 to-purple-500 bg-clip-text text-transparent mb-3">
                    PHYSICS LAB
                </h1>
                <p className="text-gray-400 text-xl tracking-widest">
                    SPH4U INTERACTIVE SIMULATION SUITE
                </p>
                <p className="text-gray-600 text-sm mt-2">Ontario Grade 12 Physics Curriculum</p>
            </motion.div>

            {/* Menu Buttons */}
            <div className="grid grid-cols-2 gap-4 w-full max-w-2xl">
                {menuItems.map((item, index) => (
                    <motion.button
                        key={item.id}
                        initial={{ y: 30, opacity: 0 }}
                        animate={{ y: 0, opacity: 1 }}
                        transition={{ delay: index * 0.1 + 0.3 }}
                        whileHover={{ scale: 1.02, y: -3 }}
                        whileTap={{ scale: 0.98 }}
                        onClick={() => onNavigate(item.id)}
                        className="group relative p-6 bg-[#111] border border-gray-800 rounded-2xl text-left overflow-hidden transition-all duration-300 hover:border-cyan-500/50"
                    >
                        <div className="absolute inset-0 bg-gradient-to-br from-cyan-500/5 to-purple-500/5 opacity-0 group-hover:opacity-100 transition-opacity" />
                        <div className="relative">
                            <span className="text-3xl mb-2 block">{item.icon}</span>
                            <span className="text-white font-bold text-lg block">{item.label}</span>
                            <span className="text-gray-500 text-sm">{item.desc}</span>
                        </div>
                    </motion.button>
                ))}
            </div>

            {/* Exit Button */}
            <motion.button
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 0.8 }}
                whileHover={{ scale: 1.02 }}
                whileTap={{ scale: 0.98 }}
                onClick={() => window.close()}
                className="mt-8 px-8 py-3 bg-[#111] border border-red-900/50 rounded-xl text-red-400 font-semibold hover:border-red-500/50 transition-all"
            >
                ✕ EXIT SYSTEM
            </motion.button>

            {/* Footer */}
            <motion.p
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 1 }}
                className="mt-12 text-gray-700 text-sm"
            >
                v3.0 • SPH4U Physics Simulator • Built with React
            </motion.p>
        </div>
    )
}

export default MainMenu
