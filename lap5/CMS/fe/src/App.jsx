import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [isKernelMode, setIsKernelMode] = useState(false);
  const [plugins, setPlugins] = useState([]);
  const [users, setUsers] = useState([]);
  const [posts, setPosts] = useState([]);
  const [newUserName, setNewUserName] = useState("");
  const [postData, setPostData] = useState({ title: '', content: '', userId: '' });

  const API_BASE = isKernelMode ? "http://localhost:8080/api/kernel" : "http://localhost:8080/api";

  useEffect(() => {
    fetchData();
  }, [isKernelMode]);

  const fetchData = async () => {
    try {
      if (isKernelMode) {
        const [pRes, plRes] = await Promise.all([
          axios.get(`${API_BASE}/posts`),
          axios.get(`${API_BASE}/plugins`)
        ]);
        setPosts(pRes.data);
        setPlugins(plRes.data);
        setUsers([]);
      } else {
        const [uRes, pRes] = await Promise.all([
          axios.get(`${API_BASE}/users`),
          axios.get(`${API_BASE}/posts`)
        ]);
        setUsers(uRes.data);
        setPosts(pRes.data);
      }
    } catch (err) { 
      console.error("Lỗi kết nối Backend!"); 
      setPosts([]); // Reset nếu lỗi
    }
  };

  const handleCreatePost = async (e) => {
    e.preventDefault();
    let payload = isKernelMode 
      ? { title: postData.title, content: postData.content }
      : { title: postData.title, content: postData.content, author: { id: parseInt(postData.userId) } };

    if (!isKernelMode && !postData.userId) return alert("Chọn tác giả!");

    try {
      await axios.post(`${API_BASE}/posts`, payload);
      setPostData({ title: '', content: '', userId: '' });
      fetchData();
      alert("Đăng bài thành công!");
    } catch (err) { alert("Lỗi đăng bài!"); }
  };

  // HÀM TOGGLE ĐÃ SỬA NÈ MYHOA
  const togglePlugin = async (id) => {
    try {
      await axios.post(`${API_BASE}/plugins/${id}/toggle`);
      // Đổi trạng thái local để nút bấm đổi màu ngay
      setPlugins(prev => prev.map(p => p.pluginId === id ? {...p, active: !p.active} : p));
      // Lấy lại posts để cập nhật nội dung sau khi plugin thay đổi
      const res = await axios.get(`${API_BASE}/posts`);
      setPosts(res.data);
    } catch (err) { alert("Lỗi kết nối Plugin!"); }
  };

  return (
    <div className={`cms-layout ${isKernelMode ? 'kernel-theme' : ''}`}>
      <aside className="sidebar">
        <div className="sidebar-brand">CMS MULTI-ARCH</div>
        
        <div className="mode-switcher">
          <button className="btn-mode" onClick={() => setIsKernelMode(!isKernelMode)}>
            {isKernelMode ? "🚀 Chế độ: Microkernel" : "🏛️ Chế độ: Layered"}
          </button>
        </div>

        <hr />

        {!isKernelMode ? (
          <div className="user-management">
            <h4>👥 Users (Database)</h4>
            <div className="add-user-box">
              <input value={newUserName} onChange={e => setNewUserName(e.target.value)} placeholder="Tên user..." />
              <button onClick={async () => {
                 await axios.post(`${API_BASE}/users`, { username: newUserName, role: 'Editor' });
                 setNewUserName(""); fetchData();
              }}>Thêm</button>
            </div>
            <ul className="mini-list">
              {users.map(u => <li key={u.id}>ID: {u.id} - {u.username}</li>)}
            </ul>
          </div>
        ) : (
          <div className="plugin-management">
            <h4>🔌 Plugins (RAM)</h4>
            {plugins.map(p => (
              <div key={p.pluginId} className="plugin-item">
                <span>{p.name}</span>
                <button 
                  onClick={() => togglePlugin(p.pluginId)} 
                  className={p.active ? 'btn-toggle active' : 'btn-toggle'}
                >
                  {p.active ? "ON" : "OFF"}
                </button>
              </div>
            ))}
          </div>
        )}
      </aside>

      <main className="main-content">
        <section className="post-form-section">
          <h3>📝 Đăng bài mới</h3>
          <form onSubmit={handleCreatePost} className="post-form">
            <div className="form-row">
              {!isKernelMode && (
                <select value={postData.userId} onChange={e => setPostData({...postData, userId: e.target.value})} required>
                  <option value="">-- Tác giả --</option>
                  {users.map(u => <option key={u.id} value={u.id}>{u.username}</option>)}
                </select>
              )}
              <input placeholder="Tiêu đề..." value={postData.title} onChange={e => setPostData({...postData, title: e.target.value})} required />
            </div>
            <textarea placeholder="Nội dung bài viết..." value={postData.content} onChange={e => setPostData({...postData, content: e.target.value})} rows="4" required />
            <button type="submit" className="btn-submit">Xuất bản bài viết</button>
          </form>
        </section>

        <section className="post-list-section">
          <h3>📋 Danh sách nội dung</h3>
          <table className="post-table">
            <thead>
              <tr>
                <th style={{width: '25%'}}>Tiêu đề</th>
                {!isKernelMode && <th style={{width: '15%'}}>Tác giả</th>}
                <th>Nội dung</th>
              </tr>
            </thead>
            <tbody>
              {posts.map((p, i) => (
                <tr key={i}>
                  <td className="fw-bold">{p.title}</td>
                  {!isKernelMode && <td><span className="user-tag">@{p.author?.username || 'Guest'}</span></td>}
                  <td><div className="content-scroll">{p.content}</div></td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </main>
    </div>
  );
}

export default App;