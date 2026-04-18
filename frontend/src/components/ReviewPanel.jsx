import IssueItem from './IssueItem';

export default function ReviewPanel({ results }) {
    const hasIssues = (results.bugs?.length > 0) ||
        (results.security?.length > 0) ||
        (results.performance?.length > 0) ||
        (results.quality?.length > 0);

    return (
        <div className="flex flex-col space-y-6">
            <div className="border-b pb-4">
                <h2 className="text-xl font-semibold text-gray-800">Review Summary</h2>
                <p className="text-gray-600 mt-2">{results.summary}</p>
            </div>

            {!hasIssues ? (
                <div className="text-green-600 font-medium">No issues found. Awesome work!</div>
            ) : (
                <div className="space-y-6">
                    <IssueSection title="Bugs" issues={results.bugs} />
                    <IssueSection title="Security" issues={results.security} />
                    <IssueSection title="Performance" issues={results.performance} />
                    <IssueSection title="Quality" issues={results.quality} />
                </div>
            )}
        </div>
    )
}

function IssueSection({ title, issues }) {
    if (!issues || issues.length === 0) return null;
    return (
        <div>
            <h3 className="text-lg font-medium text-gray-800 border-b pb-2 mb-3">
                {title} <span className="text-sm font-normal text-gray-500 bg-gray-100 px-2 py-0.5 rounded-full">{issues.length}</span>
            </h3>
            <div className="space-y-3">
                {issues.map((issue, idx) => <IssueItem key={idx} issue={issue} />)}
            </div>
        </div>
    )
}
