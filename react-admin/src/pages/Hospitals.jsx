import React from "react";
import { useState, useEffect } from "react";
import SideNav from "../components/SideNav";
import { TbLogout } from "react-icons/tb";
import { useNavigate } from "react-router-dom";

const Hospitals = () => {
  const nav= useNavigate();
  const [hospitals, setHospitals] = useState([]);
  const getHospitals = async () => {
    const res = await fetch("http://127.0.0.1:3030/api/hospital/", {
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
      const hospitalsFromServer = await getHospitals();
      setHospitals(hospitalsFromServer);
    };
    getData();
  }, []);
  console.log(hospitals);

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
          <th>Location</th>
          <th>Outpatient Clinics Working Hours</th>
        </tr>
        {hospitals.map(
          (hospital, i) =>
            hospital.user !== null && (
              <tr key={i}>
                <td>{hospital.user.name} </td>
                <td>{hospital.phone_number}</td>
                <td>
                {hospital.address.street}, {hospital.address.city}, {hospital.address.country}
                </td>
                <td>{hospital.outpatient_clinic}</td>
              </tr>
            )
        )}
      </table>
    </div>
    </>
  );
};
export default Hospitals;
