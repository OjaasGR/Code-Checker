export default function CodeEditor({ code, setCode }) {
    return (
        <div className="flex-1 flex flex-col h-full min-h-[300px]">
            <div className="bg-gray-800 text-gray-200 px-4 py-2 text-sm font-mono border-b border-gray-700 flex justify-between items-center">
                <span>Source Code</span>
            </div>
            <textarea
                className="flex-1 w-full p-4 font-mono text-sm bg-gray-900 text-gray-100 outline-none resize-none"
                placeholder="Paste your code here..."
                value={code}
                onChange={(e) => setCode(e.target.value)}
                spellCheck="false"
            />
        </div>
    )
}
