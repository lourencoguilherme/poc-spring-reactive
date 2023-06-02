import http from 'k6/http'
import { check, sleep } from 'k6'

export const options = {
    stages: [
        { target: 30, duration: '30s' },
        { target: 10, duration: '30s' },
        { target: 100, duration: '60s' },
    ],
    thresholds: {
        // throws error if more than 90% of the requests takes more than 2 seconds to be completed
        http_req_duration: [
            {
                threshold: 'p(90) < 15000',
                abortOnFail: true,
                delayAbortEval: 100
            }
        ]
    }
}

export default function() {
    const response = http.get('http://host.docker.internal:8080/test-tracing/1')
    check(response, { "status is 200": (r) => r.status === 200 })
    sleep(.300)
}