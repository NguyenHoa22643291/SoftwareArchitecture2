import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

const API_BASE = "http://localhost:8080/api";

function App() {
  const [users, setUsers] = useState([]);
  const [posts, setPosts] = useState([]);
  const [newUserName, setNewUserName] = useState("");
  const [postData, setPostData] = useState({ title: '', content: '', userId: '' });

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [uRes, pRes] = await Promise.all([
        axios.get(`${API_BASE}/users`),
        axios.get(`${API_BASE}/posts`)
      ]);
      setUsers(uRes.data);
      setPosts(pRes.data);
    } catch (err) { console.error("Lỗi kết nối API!"); }
  };

  const handleAddUser = async () => {
    if (!newUserName) return;
    await axios.post(`${API_BASE}/users`, { username: newUserName, role: 'Editor' });
    setNewUserName("");
    fetchData();
  };

  const handleCreatePost = async (e) => {
    e.preventDefault();
    if (!postData.userId) return alert("Bạn chưa chọn tác giả!");

    // Payload gửi về Backend
    const payload = {
      title: postData.title,
      content: postData.content,
      author: { id: parseInt(postData.userId) } // Gửi object kèm ID
    };

    try {
      await axios.post(`${API_BASE}/posts`, payload);
      setPostData({ title: '', content: '', userId: '' });
      fetchData();
      alert("Đăng bài thành công!");
    } catch (err) {
      console.error(err);
      alert("Backend báo lỗi JSON!");
    }
  };

  const handleDeletePost = async (id) => {
    if(window.confirm("Xóa bài này?")) {
      await axios.delete(`${API_BASE}/posts/${id}`);
      fetchData();
    }
  };

  return (
    <div className="cms-layout">
      {/* SIDEBAR TRÁI */}
      <aside className="sidebar">
        <div className="sidebar-brand">CMS ADMIN</div>
        <div className="user-management">
          <h4>👥 Quản lý User</h4>
          <div className="add-user-form">
            <input 
              value={newUserName} 
              onChange={e => setNewUserName(e.target.value)} 
              placeholder="Tên user..." 
            />
            <button onClick={handleAddUser}>Thêm</button>
          </div>
          <table className="user-table">
            <thead>
              <tr><th>ID</th><th>Tên</th></tr>
            </thead>
            <tbody>
              {users.map(u => (
                <tr key={u.id}><td>{u.id}</td><td>{u.username}</td></tr>
              ))}
            </tbody>
          </table>
        </div>
      </aside>

      {/* NỘI DUNG PHẢI */}
      <main className="main-content">
        <section className="post-form-section">
          <h3>📝 Đăng bài viết mới</h3>
          <form onSubmit={handleCreatePost} className="post-form">
            <div className="form-row">
              <select 
                value={postData.userId} 
                onChange={e => setPostData({...postData, userId: e.target.value})}
                required
              >
                <option value="">-- Chọn tác giả --</option>
                {users.map(u => <option key={u.id} value={u.id}>{u.username}</option>)}
              </select>
              <input 
                placeholder="Tiêu đề bài viết..." 
                value={postData.title}
                onChange={e => setPostData({...postData, title: e.target.value})}
                required 
              />
            </div>
            <textarea 
              placeholder="Nội dung chi tiết..." 
              value={postData.content}
              onChange={e => setPostData({...postData, content: e.target.value})}
              rows="4"
              required
            />
            <button type="submit" className="btn-submit">Xuất bản bài viết</button>
          </form>
        </section>

        <section className="post-list-section">
          <h3>📋 Danh sách nội dung chi tiết</h3>
          <table className="post-table">
            <thead>
              <tr>
                <th style={{width: '50px'}}>ID</th>
                <th style={{width: '200px'}}>Tiêu đề</th>
                <th style={{width: '120px'}}>Tác giả</th>
                <th>Nội dung</th> {/* Cột nội dung bạn yêu cầu */}
                <th style={{width: '80px'}}>Thao tác</th>
              </tr>
            </thead>
            <tbody>
              {posts.map(p => (
                <tr key={p.id}>
                  <td>{p.id}</td>
                  <td className="fw-bold">{p.title}</td>
                  <td><span className="user-tag">@{p.author?.username || 'Admin'}</span></td>
                  <td>
                    <div className="content-scroll">{p.content}</div>
                  </td>
                  <td>
                    <button onClick={() => handleDeletePost(p.id)} className="btn-del">Xóa</button>
                  </td>
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