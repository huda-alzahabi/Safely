import React from "react";
import { useState, useEffect } from "react";
import SideNav from "../components/SideNav";
import { TbLogout } from "react-icons/tb";
import { useNavigate } from "react-router-dom";

const Doctors = () => {
  const nav = useNavigate();
  const [doctors, setDoctors] = useState([]);
  const getDoctors = async () => {
    const res = await fetch("http://127.0.0.1:3030/api/doctor/all", {
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
      const doctorsFromServer = await getDoctors();
      setDoctors(doctorsFromServer);
    };
    getData();
  }, []);

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
          <h2 className="user_type">Doctors</h2>
        </div>
        <table className="all_users">
          <tr>
            <th>Name</th>
            <th>Profession</th>
            <th>Years of Experience</th>
          </tr>
          {doctors.map(
            (doctor, i) =>
              doctor.user !== null && (
                <tr key={i}>
                  <td>{doctor.user.name} </td>
                  <td>{doctor.specialty}</td>
                  <td>{doctor.years_of_experience} years</td>
                </tr>
              )
          )}
        </table>
      </div>
      </div>
    </>
  );
};
export default Doctors;
