import { motion } from 'framer-motion'

const team = [
    { name: 'SHAN TRUONG', role: 'Project Lead • Senior Developer', color: 'from-cyan-400 to-blue-500' },
    { name: 'ARYAN VERMA', role: 'Physics Architect • Technical Lead', color: 'from-purple-400 to-pink-500' },
    { name: 'JERRY WU', role: 'System Analyst • QA Operations', color: 'from-pink-400 to-red-500' },
]

const Credits = ({ onBack }) => {
    return (
        <div className="min-h-screen p-8 flex flex-col items-center justify-center">
            {/* Header */}
            <div className="absolute top-8 left-8">
                <motion.button
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                    onClick={onBack}
                    className="px-6 py-2 bg-[#1a1a1a] border border-gray-800 rounded-lg text-gray-400 hover:text-white hover:border-cyan-500/50 transition-all"
                >
                    ← BACK
                </motion.button>
            </div>

            <motion.h1
                initial={{ y: -30, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                className="text-4xl font-bold text-cyan-400 mb-12"
            >
                LAB PERSONNEL
            </motion.h1>

            <div className="flex flex-col gap-8 w-full max-w-md">
                {team.map((member, i) => (
                    <motion.div
                        key={member.name}
                        initial={{ y: 50, opacity: 0 }}
                        animate={{ y: 0, opacity: 1 }}
                        transition={{ delay: i * 0.15 }}
                        className="text-center"
                    >
                        <h2 className={`text-3xl font-bold bg-gradient-to-r ${member.color} bg-clip-text text-transparent`}>
                            {member.name}
                        </h2>
                        <p className="text-gray-500 mt-1">{member.role}</p>
                    </motion.div>
                ))}
            </div>

            <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 0.8 }}
                className="mt-16 text-center"
            >
                <p className="text-gray-600 text-sm">Collision Lab v2.0</p>
                <p className="text-gray-700 text-xs mt-1">Built with React, Tailwind CSS & Framer Motion</p>
            </motion.div>
        </div>
    )
}

export default Credits
