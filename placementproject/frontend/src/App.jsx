import { useEffect, useState } from 'react'
import { AnimatePresence, motion } from 'framer-motion'
import { Link, Navigate, Route, Routes, useLocation, useNavigate } from 'react-router-dom'
import {
  ArrowRight, BriefcaseBusiness, Building2, CheckCircle2,
  ChevronRight, Clock3, GraduationCap, LayoutDashboard,
  LoaderCircle, LogOut, MapPin, Menu, Search, Send, Sparkles, Users, X
} from 'lucide-react'
import { api } from './api'

const emptyJobs = []
const fade = { initial: { opacity: 0, y: 18 }, animate: { opacity: 1, y: 0 }, exit: { opacity: 0, y: -10 } }

function useSession() {
  const [user, setUser] = useState(() => {
    try { return JSON.parse(localStorage.getItem('placement-user')) } catch { return null }
  })
  const save = (next) => {
    setUser(next)
    if (next) localStorage.setItem('placement-user', JSON.stringify(next))
    else localStorage.removeItem('placement-user')
  }
  return [user, save]
}

function App() {
  const [user, setUser] = useSession()
  const [toast, setToast] = useState(null)
  const location = useLocation()

  const notify = (message, type = 'success') => {
    setToast({ message, type })
    window.setTimeout(() => setToast(null), 3400)
  }

  return (
    <div className="app-shell">
      <div className="ambient ambient-one" />
      <div className="ambient ambient-two" />
      <Navbar user={user} onLogout={() => { setUser(null); notify('Signed out successfully') }} />
      <AnimatePresence mode="wait">
        <motion.main key={location.pathname} {...fade} transition={{ duration: .38, ease: [0.22, 1, 0.36, 1] }}>
          <Routes location={location}>
            <Route path="/" element={<HomePage user={user} notify={notify} />} />
            <Route path="/login" element={<AuthPage mode="login" setUser={setUser} notify={notify} />} />
            <Route path="/register" element={<AuthPage mode="register" setUser={setUser} notify={notify} />} />
            <Route path="/student" element={user?.role === 'STUDENT' ? <StudentDashboard user={user} notify={notify} /> : <Navigate to="/login" />} />
            <Route path="/recruiter" element={user?.role === 'RECRUITER' ? <RecruiterDashboard user={user} notify={notify} /> : <Navigate to="/login" />} />
            <Route path="*" element={<Navigate to="/" />} />
          </Routes>
        </motion.main>
      </AnimatePresence>
      <AnimatePresence>{toast && <Toast {...toast} />}</AnimatePresence>
    </div>
  )
}

function Navbar({ user, onLogout }) {
  const [open, setOpen] = useState(false)
  const dashboard = user?.role === 'STUDENT' ? '/student' : '/recruiter'
  return (
    <header className="nav-wrap">
      <nav className="nav container">
        <Link className="brand" to="/" onClick={() => setOpen(false)}>
          <span className="brand-mark"><Sparkles size={20} /></span>
          <span>Launch<span>pad</span></span>
        </Link>
        <button className="icon-button mobile-menu" onClick={() => setOpen(!open)} aria-label="Toggle menu">
          {open ? <X /> : <Menu />}
        </button>
        <div className={`nav-links ${open ? 'open' : ''}`}>
          <Link to="/" onClick={() => setOpen(false)}>Opportunities</Link>
          {user ? (
            <>
              <Link to={dashboard} onClick={() => setOpen(false)}><LayoutDashboard size={17} /> Dashboard</Link>
              <button className="button button-ghost" onClick={() => { onLogout(); setOpen(false) }}><LogOut size={17} /> Sign out</button>
            </>
          ) : (
            <>
              <Link to="/login" onClick={() => setOpen(false)}>Sign in</Link>
              <Link className="button button-primary nav-cta" to="/register" onClick={() => setOpen(false)}>Create account <ArrowRight size={17} /></Link>
            </>
          )}
        </div>
      </nav>
    </header>
  )
}

function HomePage({ user, notify }) {
  const [jobs, setJobs] = useState(emptyJobs)
  const [filters, setFilters] = useState({ keyword: '', location: '', industry: '' })
  const [loading, setLoading] = useState(true)

  const load = async (next = filters) => {
    setLoading(true)
    try {
      const data = await api.searchJobs({ ...next, page: 0, size: 12 })
      setJobs(data.content || [])
    } catch (error) { notify(error.message, 'error') }
    finally { setLoading(false) }
  }
  useEffect(() => { load({ keyword: '', location: '', industry: '' }) }, [])

  return (
    <>
      <section className="hero container">
        <motion.div className="hero-copy" initial={{ opacity: 0, x: -30 }} animate={{ opacity: 1, x: 0 }} transition={{ duration: .65 }}>
          <div className="eyebrow"><span /> Careers begin with one good move</div>
          <h1>Find work that moves your <em>future</em> forward.</h1>
          <p>Discover internships and graduate roles from companies looking for emerging talent.</p>
          <div className="hero-actions">
            <a className="button button-primary" href="#opportunities">Explore roles <ArrowRight size={18} /></a>
            {!user && <Link className="button button-ghost" to="/register">Join the portal</Link>}
          </div>
          <div className="trust-row">
            <span><CheckCircle2 /> Direct applications</span>
            <span><CheckCircle2 /> Live status tracking</span>
          </div>
        </motion.div>
        <motion.div className="hero-visual" initial={{ opacity: 0, scale: .92 }} animate={{ opacity: 1, scale: 1 }} transition={{ duration: .75, delay: .1 }}>
          <div className="orbit orbit-one" /><div className="orbit orbit-two" />
          <div className="career-card main-card">
            <div className="card-top"><span className="company-logo">TS</span><span className="live-dot">New</span></div>
            <h3>Backend Engineer Intern</h3><p>TechSoft · Pune</p>
            <div className="skill-row"><span>Java</span><span>Spring Boot</span><span>MySQL</span></div>
            <div className="match"><span>Profile match</span><strong>92%</strong></div>
            <div className="match-bar"><span /></div>
          </div>
          <motion.div className="float-card float-left" animate={{ y: [0, -10, 0] }} transition={{ repeat: Infinity, duration: 4 }}><Users /><div><strong>500+</strong><span>Active learners</span></div></motion.div>
          <motion.div className="float-card float-right" animate={{ y: [0, 9, 0] }} transition={{ repeat: Infinity, duration: 4.5 }}><BriefcaseBusiness /><div><strong>Fresh roles</strong><span>Added every week</span></div></motion.div>
        </motion.div>
      </section>

      <section className="jobs-section container" id="opportunities">
        <div className="section-heading"><div><span className="kicker">Opportunity board</span><h2>Your next role, in focus.</h2></div><span className="result-count">{jobs.length} roles found</span></div>
        <form className="search-panel" onSubmit={(e) => { e.preventDefault(); load() }}>
          <label><Search size={18} /><input value={filters.keyword} onChange={e => setFilters({ ...filters, keyword: e.target.value })} placeholder="Role or keyword" /></label>
          <label><MapPin size={18} /><input value={filters.location} onChange={e => setFilters({ ...filters, location: e.target.value })} placeholder="Location" /></label>
          <label><Building2 size={18} /><input value={filters.industry} onChange={e => setFilters({ ...filters, industry: e.target.value })} placeholder="Industry" /></label>
          <button className="button button-primary" type="submit">Search</button>
        </form>
        {loading ? <Loading /> : jobs.length ? <div className="job-grid">{jobs.map((job, i) => <JobCard key={job.id} job={job} index={i} user={user} notify={notify} />)}</div> : <Empty title="No roles found" text="Try clearing a filter or searching a broader keyword." />}
      </section>
    </>
  )
}

function JobCard({ job, index, user, notify, onSelect }) {
  const navigate = useNavigate()
  const apply = async () => {
    if (!user) return navigate('/login')
    if (user.role !== 'STUDENT') return notify('Only student accounts can apply', 'error')
    try { await api.apply(user.id, job.id); notify(`Application sent to ${job.companyName}`) }
    catch (error) { notify(error.message, 'error') }
  }
  return (
    <motion.article className="job-card" initial={{ opacity: 0, y: 20 }} whileInView={{ opacity: 1, y: 0 }} viewport={{ once: true }} transition={{ delay: Math.min(index * .05, .3) }} whileHover={{ y: -6 }}>
      <div className="job-head"><span className="company-logo">{initials(job.companyName)}</span><span className="deadline"><Clock3 /> {formatDate(job.applicationDeadline)}</span></div>
      <div><span className="company-name">{job.companyName}</span><h3>{job.title}</h3></div>
      <div className="job-meta"><span><MapPin /> {job.location || 'Remote'}</span><span><BriefcaseBusiness /> {job.industry || 'General'}</span></div>
      <p className="job-description">{job.description || 'A new opportunity for ambitious students ready to learn and contribute.'}</p>
      <div className="skill-row">{(job.requiredSkills || 'Open to learners').split(',').slice(0, 3).map(skill => <span key={skill}>{skill.trim()}</span>)}</div>
      <div className="job-foot"><div><small>Stipend</small><strong>{job.stipend ? `₹${Number(job.stipend).toLocaleString('en-IN')}/mo` : 'Not disclosed'}</strong></div><button className="icon-action" onClick={onSelect ? () => onSelect(job) : apply} aria-label={onSelect ? 'View applicants' : 'Apply now'}><ChevronRight /></button></div>
    </motion.article>
  )
}

function AuthPage({ mode, setUser, notify }) {
  const isRegister = mode === 'register'
  const [role, setRole] = useState('STUDENT')
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  const submit = async (event) => {
    event.preventDefault()
    setLoading(true)
    const values = Object.fromEntries(new FormData(event.currentTarget))
    try {
      let data
      if (!isRegister) data = await api.login(values)
      else if (role === 'STUDENT') data = await api.registerStudent({ ...values, gpa: values.gpa ? Number(values.gpa) : null })
      else data = await api.registerCompany(values)
      setUser(data)
      notify(isRegister ? 'Your account is ready' : `Welcome back, ${data.displayName}`)
      navigate(data.role === 'STUDENT' ? '/student' : '/recruiter')
    } catch (error) { notify(error.message, 'error') }
    finally { setLoading(false) }
  }

  return (
    <section className="auth-layout container">
      <div className="auth-story">
        <span className="kicker">{isRegister ? 'Start your journey' : 'Welcome back'}</span>
        <h1>{isRegister ? 'Build the career you have been preparing for.' : 'Pick up where your ambition left off.'}</h1>
        <p>One focused space for opportunities, applications, and the people moving careers forward.</p>
        <div className="auth-proof"><div><GraduationCap /><span><strong>For students</strong>Find and track the right openings.</span></div><div><Building2 /><span><strong>For recruiters</strong>Post roles and review applicants.</span></div></div>
      </div>
      <motion.div className="form-card" initial={{ opacity: 0, x: 25 }} animate={{ opacity: 1, x: 0 }}>
        <div className="form-heading"><h2>{isRegister ? 'Create your account' : 'Sign in'}</h2><p>{isRegister ? 'Choose your role and enter your details.' : 'Use your registered email and password.'}</p></div>
        {isRegister && <div className="role-switch"><button className={role === 'STUDENT' ? 'active' : ''} onClick={() => setRole('STUDENT')}><GraduationCap /> Student</button><button className={role === 'RECRUITER' ? 'active' : ''} onClick={() => setRole('RECRUITER')}><Building2 /> Recruiter</button></div>}
        <form onSubmit={submit} className="stack-form">
          {isRegister && role === 'STUDENT' && <div className="two-col"><Field label="First name" name="firstName" required /><Field label="Last name" name="lastName" required /></div>}
          {isRegister && role === 'RECRUITER' && <Field label="Company name" name="companyName" required />}
          <Field label="Email address" name="email" type="email" required />
          <Field label="Password" name="password" type="password" minLength="8" required />
          {isRegister && role === 'STUDENT' && <><div className="two-col"><Field label="University" name="university" /><Field label="Major" name="major" /></div><div className="two-col"><Field label="GPA" name="gpa" type="number" step="0.01" /><Field label="Skills" name="technicalSkills" placeholder="Java, React, MySQL" /></div><Field label="Resume URL" name="resumeUrl" type="url" /></>}
          {isRegister && role === 'RECRUITER' && <><div className="two-col"><Field label="Industry" name="industry" /><Field label="Website" name="website" type="url" /></div><Field label="About company" name="description" /></>}
          <button className="button button-primary submit-button" disabled={loading}>{loading ? <LoaderCircle className="spin" /> : isRegister ? 'Create account' : 'Sign in'} {!loading && <ArrowRight size={18} />}</button>
        </form>
        <p className="form-alternate">{isRegister ? 'Already registered?' : 'New to Launchpad?'} <Link to={isRegister ? '/login' : '/register'}>{isRegister ? 'Sign in' : 'Create account'}</Link></p>
      </motion.div>
    </section>
  )
}

function StudentDashboard({ user, notify }) {
  const [tab, setTab] = useState('discover')
  const [jobs, setJobs] = useState([])
  const [applications, setApplications] = useState([])
  const [loading, setLoading] = useState(true)
  const load = async () => {
    setLoading(true)
    try {
      const [jobData, applicationData] = await Promise.all([api.searchJobs({ page: 0, size: 50 }), api.studentApplications(user.id)])
      setJobs(jobData.content || []); setApplications(applicationData || [])
    } catch (error) { notify(error.message, 'error') }
    finally { setLoading(false) }
  }
  useEffect(() => { load() }, [])
  return (
    <DashboardFrame icon={<GraduationCap />} eyebrow="Student workspace" title={`Good to see you, ${user.displayName.split(' ')[0]}.`} subtitle="Keep your applications moving and discover what is next.">
      <Stats items={[{ label: 'Applications', value: applications.length, icon: <Send /> }, { label: 'In review', value: applications.filter(a => ['PENDING','REVIEWED'].includes(a.status)).length, icon: <Clock3 /> }, { label: 'Available roles', value: jobs.length, icon: <BriefcaseBusiness /> }]} />
      <Tabs active={tab} setActive={setTab} items={[['discover','Discover roles'],['applications','My applications']]} />
      {loading ? <Loading /> : tab === 'discover' ? <div className="job-grid compact">{jobs.map((job, i) => <JobCard key={job.id} job={job} index={i} user={user} notify={(msg, type) => { notify(msg, type); if (!type || type === 'success') load() }} />)}</div> : <ApplicationList applications={applications} />}
    </DashboardFrame>
  )
}

function RecruiterDashboard({ user, notify }) {
  const [jobs, setJobs] = useState([])
  const [selected, setSelected] = useState(null)
  const [applicants, setApplicants] = useState([])
  const [showForm, setShowForm] = useState(false)
  const loadJobs = async () => {
    try { const data = await api.searchJobs({ page: 0, size: 100 }); setJobs((data.content || []).filter(job => job.companyId === user.id)) }
    catch (error) { notify(error.message, 'error') }
  }
  useEffect(() => { loadJobs() }, [])
  const viewApplicants = async (job) => {
    setSelected(job)
    try { setApplicants(await api.opportunityApplications(job.id)) } catch (error) { notify(error.message, 'error') }
  }
  const update = async (application, status) => {
    try { await api.updateStatus(application.studentId, application.opportunityId, status); notify(`Application marked ${status.toLowerCase()}`); viewApplicants(selected) }
    catch (error) { notify(error.message, 'error') }
  }
  return (
    <DashboardFrame icon={<Building2 />} eyebrow="Recruiter workspace" title={user.displayName} subtitle="Publish opportunities and move the strongest applicants forward." action={<button className="button button-primary" onClick={() => setShowForm(true)}>Post opportunity <ArrowRight size={18} /></button>}>
      <Stats items={[{ label: 'Published roles', value: jobs.length, icon: <BriefcaseBusiness /> }, { label: 'Total applicants', value: jobs.reduce((sum, job) => sum + (job.currentApplicants || 0), 0), icon: <Users /> }, { label: 'Open capacity', value: jobs.reduce((sum, job) => sum + Math.max((job.maxApplicants || 0) - (job.currentApplicants || 0), 0), 0), icon: <LayoutDashboard /> }]} />
      <div className="workspace-grid">
        <section><div className="mini-heading"><h2>Your opportunities</h2><span>{jobs.length} published</span></div>{jobs.length ? <div className="job-grid compact">{jobs.map((job, i) => <JobCard key={job.id} job={job} index={i} user={user} notify={notify} onSelect={viewApplicants} />)}</div> : <Empty title="No roles published" text="Post your first opportunity to start receiving applications." />}</section>
        <aside className="applicant-panel"><div className="mini-heading"><h2>Applicants</h2>{selected && <span>{selected.title}</span>}</div>{!selected ? <Empty title="Select an opportunity" text="Choose a role to review its applicants." /> : applicants.length ? <div className="applicant-list">{applicants.map(app => <div className="applicant" key={`${app.studentId}-${app.opportunityId}`}><span className="avatar">{app.studentId}</span><div><strong>Student #{app.studentId}</strong><small>{formatDate(app.appliedAt)} · {app.status}</small></div><select value={app.status} onChange={e => update(app, e.target.value)}><option>PENDING</option><option>REVIEWED</option><option>ACCEPTED</option><option>REJECTED</option></select></div>)}</div> : <Empty title="No applications yet" text="New applicants will appear here." />}</aside>
      </div>
      <AnimatePresence>{showForm && <JobForm companyId={user.id} onClose={() => setShowForm(false)} onCreated={() => { setShowForm(false); loadJobs(); notify('Opportunity published') }} notify={notify} />}</AnimatePresence>
    </DashboardFrame>
  )
}

function JobForm({ companyId, onClose, onCreated, notify }) {
  const [loading, setLoading] = useState(false)
  const submit = async (event) => {
    event.preventDefault(); setLoading(true)
    const values = Object.fromEntries(new FormData(event.currentTarget))
    try { await api.createJob({ ...values, companyId, stipend: values.stipend ? Number(values.stipend) : null, maxApplicants: Number(values.maxApplicants) }); onCreated() }
    catch (error) { notify(error.message, 'error') }
    finally { setLoading(false) }
  }
  return <motion.div className="modal-backdrop" initial={{ opacity: 0 }} animate={{ opacity: 1 }} exit={{ opacity: 0 }} onMouseDown={onClose}><motion.div className="modal" initial={{ opacity: 0, scale: .94, y: 20 }} animate={{ opacity: 1, scale: 1, y: 0 }} exit={{ opacity: 0, scale: .96 }} onMouseDown={e => e.stopPropagation()}><div className="modal-head"><div><span className="kicker">New opening</span><h2>Post an opportunity</h2></div><button className="icon-button" onClick={onClose}><X /></button></div><form className="stack-form" onSubmit={submit}><Field label="Role title" name="title" required /><Field label="Description" name="description" required /><div className="two-col"><Field label="Industry" name="industry" /><Field label="Location" name="location" /></div><Field label="Required skills" name="requiredSkills" placeholder="Java, Spring Boot, MySQL" /><div className="two-col"><Field label="Monthly stipend" name="stipend" type="number" /><Field label="Maximum applicants" name="maxApplicants" type="number" required /></div><Field label="Application deadline" name="applicationDeadline" type="date" required /><button className="button button-primary submit-button" disabled={loading}>{loading ? <LoaderCircle className="spin" /> : 'Publish opportunity'} {!loading && <Send size={18} />}</button></form></motion.div></motion.div>
}

function DashboardFrame({ icon, eyebrow, title, subtitle, action, children }) {
  return <section className="dashboard container"><div className="dashboard-head"><div className="dashboard-title"><span className="dashboard-icon">{icon}</span><div><span className="kicker">{eyebrow}</span><h1>{title}</h1><p>{subtitle}</p></div></div>{action}</div>{children}</section>
}

function Stats({ items }) { return <div className="stats-grid">{items.map(item => <motion.div className="stat-card" key={item.label} whileHover={{ y: -4 }}><span>{item.icon}</span><div><strong>{item.value}</strong><small>{item.label}</small></div></motion.div>)}</div> }
function Tabs({ items, active, setActive }) { return <div className="tabs">{items.map(([id, label]) => <button className={active === id ? 'active' : ''} key={id} onClick={() => setActive(id)}>{label}{active === id && <motion.span layoutId="tab" />}</button>)}</div> }
function ApplicationList({ applications }) { return applications.length ? <div className="application-table">{applications.map(app => <div className="application-row" key={`${app.studentId}-${app.opportunityId}`}><span className="company-logo"><BriefcaseBusiness /></span><div><strong>{app.opportunityTitle}</strong><small>Applied {formatDate(app.appliedAt)}</small></div><Status value={app.status} /></div>)}</div> : <Empty title="No applications yet" text="Explore available roles and send your first application." /> }
function Status({ value }) { return <span className={`status status-${value.toLowerCase()}`}>{value}</span> }
function Field({ label, ...props }) { return <label className="field"><span>{label}</span><input {...props} /></label> }
function Loading() { return <div className="loading"><LoaderCircle className="spin" /><span>Loading opportunities...</span></div> }
function Empty({ title, text }) { return <div className="empty"><span><Search /></span><h3>{title}</h3><p>{text}</p></div> }
function Toast({ message, type }) { return <motion.div className={`toast ${type}`} initial={{ opacity: 0, x: 30, y: 10 }} animate={{ opacity: 1, x: 0, y: 0 }} exit={{ opacity: 0, x: 30 }}><CheckCircle2 /><span>{message}</span></motion.div> }
function initials(name = '') { return name.split(' ').map(word => word[0]).join('').slice(0, 2).toUpperCase() || 'CO' }
function formatDate(value) { if (!value) return 'Open'; const date = new Date(value); return Number.isNaN(date.getTime()) ? value : date.toLocaleDateString('en-IN', { day: 'numeric', month: 'short', year: 'numeric' }) }

export default App
