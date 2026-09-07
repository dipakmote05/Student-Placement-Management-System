const API_URL = import.meta.env.VITE_API_URL || ''

async function request(path, options = {}) {
  const response = await fetch(`${API_URL}${path}`, {
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...options.headers
    }
  })

  const type = response.headers.get('content-type') || ''
  const body = type.includes('application/json') ? await response.json() : await response.text()

  if (!response.ok) {
    throw new Error(body?.message || body || 'Something went wrong')
  }
  return body
}

export const api = {
  login: (payload) => request('/api/auth/login', { method: 'POST', body: JSON.stringify(payload) }),
  registerStudent: (payload) => request('/api/auth/register/student', { method: 'POST', body: JSON.stringify(payload) }),
  registerCompany: (payload) => request('/api/auth/register/company', { method: 'POST', body: JSON.stringify(payload) }),
  searchJobs: (params = {}) => {
    const query = new URLSearchParams(Object.entries(params).filter(([, value]) => value !== '' && value != null))
    return request(`/api/opportunities/search?${query}`)
  },
  createJob: (payload) => request('/api/opportunities', { method: 'POST', body: JSON.stringify(payload) }),
  apply: (studentId, opportunityId) => request(`/api/applications/apply?studentId=${studentId}&opportunityId=${opportunityId}`, { method: 'POST' }),
  studentApplications: (studentId) => request(`/api/applications/student/${studentId}`),
  opportunityApplications: (opportunityId) => request(`/api/applications/opportunity/${opportunityId}`),
  updateStatus: (studentId, opportunityId, status) => request(`/api/applications/${studentId}/${opportunityId}/status?status=${status}`, { method: 'PATCH' })
}
