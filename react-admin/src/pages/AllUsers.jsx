import React from "react";
import { useState, useEffect } from "react";
import { FaEdit } from "react-icons/fa";
import { MdDeleteForever } from "react-icons/md";
import SideNav from "../components/SideNav";

const Users = () => {
  var isUpdated = false;
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


    const data = await res.json();
    console.log(data);
    alert("User Successfully Deleted");
    isUpdated = !isUpdated;
  };

  return (
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
               
              />
            </td>
          </tr>
        ))}
      </table>
    </div>
  );
export default Users;
