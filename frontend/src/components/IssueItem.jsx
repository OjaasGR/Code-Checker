export default function IssueItem({ issue }) {
    let severityColor = "bg-gray-100 text-gray-800 border-gray-200";
    if (issue.severity?.toUpperCase() === 'HIGH') {
        severityColor = "bg-red-50 text-red-800 border-red-200";
    } else if (issue.severity?.toUpperCase() === 'MEDIUM') {
        severityColor = "bg-yellow-50 text-yellow-800 border-yellow-200";
    } else if (issue.severity?.toUpperCase() === 'LOW') {
        severityColor = "bg-blue-50 text-blue-800 border-blue-200";
    }

    return (
        <div className={`p-4 border rounded-lg ${severityColor} flex flex-col space-y-2 text-sm`}>
            <div className="flex justify-between items-start">
                <span className="font-semibold">{issue.severity?.toUpperCase()} SEVERITY</span>
                {issue.line && <span className="bg-white/60 px-2 py-0.5 rounded text-xs">Line {issue.line}</span>}
            </div>
            <p className="text-gray-900"><span className="font-medium">Issue:</span> {issue.issue}</p>
            {issue.explanation && <p className="text-gray-700 italic">{issue.explanation}</p>}
            {issue.fix && (
                <div className="mt-2 bg-white/60 p-2 rounded border border-white/40">
                    <span className="font-medium block text-gray-900 mb-1">Suggested Fix:</span>
                    <code className="text-sm rounded break-words whitespace-pre-wrap font-mono">{issue.fix}</code>
                </div>
            )}
        </div>
    )
}
