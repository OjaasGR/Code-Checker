import { useState } from 'react'
import axios from 'axios'
import CodeEditor from './components/CodeEditor'
import ReviewPanel from './components/ReviewPanel'

export default function App() {
    const [code, setCode] = useState('')
    const [results, setResults] = useState(null)
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState(null)

    const handleReview = async () => {
        if (!code.trim()) return
        setLoading(true)
        setError(null)
        try {
            const response = await axios.post('http://localhost:8080/api/review', { code })
            setResults(response.data)
        } catch (err) {
            setError(err.response?.data?.summary || 'An error occurred while fetching the review.')
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className="min-h-screen bg-gray-100 p-4 md:p-8 flex flex-col items-center">
            <h1 className="text-3xl font-bold text-gray-800 mb-6">LLM-Powered Code Review Assistant</h1>

            <div className="w-full max-w-7xl flex flex-col lg:flex-row gap-6 h-[80vh]">
                <div className="w-full lg:w-1/2 flex flex-col bg-white rounded-xl shadow-lg border border-gray-200 overflow-hidden">
                    <CodeEditor code={code} setCode={setCode} />
                    <div className="p-4 bg-gray-50 border-t flex justify-end">
                        <button
                            onClick={handleReview}
                            disabled={loading || !code.trim()}
                            className="px-6 py-2 bg-blue-600 hover:bg-blue-700 text-white font-medium rounded-lg shadow-sm transition-colors disabled:opacity-50"
                        >
                            {loading ? 'Reviewing...' : 'Review Code'}
                        </button>
                    </div>
                </div>

                <div className="w-full lg:w-1/2 flex flex-col bg-white rounded-xl shadow-lg border border-gray-200 overflow-hidden p-6 space-y-4 overflow-y-auto w-full">
                    {loading && <div className="text-gray-500 flex items-center justify-center h-full">Analyzing your code...</div>}
                    {error && <div className="text-red-600 bg-red-50 p-4 rounded-lg">{error}</div>}
                    {!loading && !error && results && <ReviewPanel results={results} />}
                    {!loading && !error && !results && <div className="text-gray-400 flex items-center justify-center h-full text-center">Paste code and click Review to see suggestions.</div>}
                </div>
            </div>
        </div>
    )
}
