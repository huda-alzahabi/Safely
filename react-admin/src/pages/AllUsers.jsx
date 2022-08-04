import React from "react";
import { useState, useEffect } from "react";
import { FaEdit } from "react-icons/fa";
import { MdDeleteForever } from "react-icons/md";
import SideNav from "../components/SideNav";
import { TbLogout } from "react-icons/tb";
import { useNavigate } from "react-router-dom";

const Users = () => {
  var isUpdated = false;
  const nav = useNavigate();
  const [users, setUsers] = useState([]);
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
    };
    getData();
  }, [isUpdated]);

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
    console.log(data);
    alert("User Successfully Deleted");
    isUpdated = !isUpdated;
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
        <table className="titleMargin">
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
                <FaEdit />
              </td>
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
    </>
  );
};
export default Users;
