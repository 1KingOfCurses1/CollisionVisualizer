import { useState } from 'react'
import { motion, AnimatePresence } from 'framer-motion'
import MainMenu from './components/MainMenu'
import SimulationHub from './components/SimulationHub'
import CollisionSim from './components/simulations/CollisionSim'
import ProjectileSim from './components/simulations/ProjectileSim'
import GravitySim from './components/simulations/GravitySim'
import ElectricFieldSim from './components/simulations/ElectricFieldSim'
import WaveSim from './components/simulations/WaveSim'
import Instructions from './components/Instructions'
import Dictionary from './components/Dictionary'
import Credits from './components/Credits'
import './App.css'

function App() {
  const [currentPage, setCurrentPage] = useState('menu')

  const pageVariants = {
    initial: { opacity: 0, x: 50 },
    in: { opacity: 1, x: 0 },
    out: { opacity: 0, x: -50 }
  }

  const renderPage = () => {
    switch (currentPage) {
      case 'simulations':
        return <SimulationHub onBack={() => setCurrentPage('menu')} onSelect={setCurrentPage} />
      case 'collision':
        return <CollisionSim onBack={() => setCurrentPage('simulations')} />
      case 'projectile':
        return <ProjectileSim onBack={() => setCurrentPage('simulations')} />
      case 'gravity':
        return <GravitySim onBack={() => setCurrentPage('simulations')} />
      case 'electric':
        return <ElectricFieldSim onBack={() => setCurrentPage('simulations')} />
      case 'wave':
        return <WaveSim onBack={() => setCurrentPage('simulations')} />
      case 'instructions':
        return <Instructions onBack={() => setCurrentPage('menu')} />
      case 'dictionary':
        return <Dictionary onBack={() => setCurrentPage('menu')} />
      case 'credits':
        return <Credits onBack={() => setCurrentPage('menu')} />
      default:
        return <MainMenu onNavigate={setCurrentPage} />
    }
  }

  return (
    <div className="min-h-screen bg-[#0a0a0a] overflow-hidden">
      {/* Background Grid */}
      <div className="fixed inset-0 pointer-events-none">
        <div className="absolute inset-0" style={{
          backgroundImage: 'linear-gradient(rgba(255,255,255,0.02) 1px, transparent 1px), linear-gradient(90deg, rgba(255,255,255,0.02) 1px, transparent 1px)',
          backgroundSize: '50px 50px'
        }} />
      </div>

      <AnimatePresence mode="wait">
        <motion.div
          key={currentPage}
          initial="initial"
          animate="in"
          exit="out"
          variants={pageVariants}
          transition={{ duration: 0.25 }}
          className="relative z-10"
        >
          {renderPage()}
        </motion.div>
      </AnimatePresence>
    </div>
  )
}

export default App
