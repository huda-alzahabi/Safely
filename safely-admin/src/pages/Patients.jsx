import React from "react";
import { useState, useEffect } from "react";
import SideNav from "../components/SideNav";
import { TbLogout } from "react-icons/tb";
import { useNavigate } from "react-router-dom";


const Patients = () => {
  const nav = useNavigate();

  const [patients, setPatients] = useState([]);
  const getPatients = async () => {
    const res = await fetch("http://127.0.0.1:3030/api/patient/get", {
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
      const patientsFromServer = await getPatients();
      setPatients(patientsFromServer);
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
      <table className="titleMargin">
        <tr>
          <th>Name</th>
          <th>Phone Number</th>
          <th>Nationality</th>
        </tr>
        {patients.map(
          (patient, i) =>
            patient.user !== null && (
              <tr key={i}>
                <td>{patient.user.name} </td>
                <td>{patient.phone_number} </td>
                <td>{patient.nationality}</td>
              </tr>
            )
        )}
      </table>
    </div>
    </>
  );
};
export default Patients;
