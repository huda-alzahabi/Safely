import React from "react";
import { useState, useEffect } from "react";
import { FaEdit } from "react-icons/fa";
import { MdDeleteForever } from "react-icons/md";
import SideNav from "../components/SideNav";
import {TbLogout} from "react-icons/tb"

const Doctors = () => {
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
  console.log(doctors);

  return (
    <>
    <TbLogout className="logout"/>
    <div className="charts">
      <SideNav />
      <table className="titleMargin">
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Profession</th>
          <th>Years of Experience</th>
        </tr>
        {doctors.map(
          (doctor, i) =>
            doctor.user !== null && (
              <tr key={i}>
                <td>{doctor.user.name} </td>
                <td>{doctor.user.email} </td>
                <td>{doctor.specialty}</td>
                <td>{doctor.years_of_experience} years</td>
                <td>
                  <FaEdit />
                </td>
                <td>
                  <MdDeleteForever />
                </td>
              </tr>
            )
        )}
      </table>
    </div>
    </>
  );
};
export default Doctors;
