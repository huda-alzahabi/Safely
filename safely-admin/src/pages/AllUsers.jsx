import React from "react";
import { useState, useEffect } from "react";
import { MdDeleteForever } from "react-icons/md";
import SideNav from "../components/SideNav";
import { TbLogout } from "react-icons/tb";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";

const Users = () => {
  const nav = useNavigate();

  const [users, setUsers] = useState([]);
  const [usersFiltered, setFiltered] = useState([]);
  const [isUpdated, setIsUpdated] = useState(false);


  const getUsers = async () => {
    const res = await fetch("http://127.0.0.1:3030/api/user/auth/get", {
      headers: {
        "content-type": "application/json",
        token: localStorage.getItem("Bearer"),
      },
    });
    const data = await res.json();
    return data;
  };

  useEffect(() => {
    const getData = async () => {
      const usersFromServer = await getUsers();
      setUsers(usersFromServer);
      setFiltered(usersFromServer);
    };
    getData();
  }, [isUpdated,]);

  const deleteUser = async (id) => {
    const res = await fetch(
      `http://127.0.0.1:3030/api/user/auth/delete?id=` + id,
      {
        method: "delete",
        headers: {
          token: localStorage.getItem("Bearer"),
          "content-type": "application/json",
        },
      }
    );
    const data = await res.json();
    toast.success("User Successfully Deleted");
    setIsUpdated(!isUpdated);
  };
  const filterUsers = (e) => {
    let value = e.target.value.toLowerCase();
    let result = [];
    result = usersFiltered.filter((users) => {
      return (
        users.name.search(value) != -1 ||
        users.email.search(value) != -1 ||
        users.userType.search(value) != -1
      );
    });
    setUsers(result);
  };

  const logout = () => {
    localStorage.removeItem("user_id");
    localStorage.removeItem("Bearer");
    nav("/");
  };

  return (
    <>
        <TbLogout className="logout" role="button" onClick={logout} />
      <div className="charts">
        <SideNav />
        <div className="searchTitle">
        <div className="filter">
          <label className="search">
            Search Users</label>
            <input
              className="box"
              type="text"
              onChange={(e) => filterUsers(e)}
            />
        </div>
        <table className="all_users" >
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>User Type</th>
          </tr>
          {users.map((user, i) => (
            <tr key={i}>
              <td>{user.name} </td>
              <td>{user.email}</td>
              <td>{user.userType} </td>
              <td>
                <MdDeleteForever
                  role="button"
                  onClick={() => deleteUser(user._id)}
                />
              </td>
            </tr>
          ))}
        </table>
      </div>
        </div>
    </>
  );
};
export default Users;
