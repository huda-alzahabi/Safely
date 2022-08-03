import React from "react";
import { useState, useEffect } from "react";
import { FaEdit } from "react-icons/fa";
import { MdDeleteForever } from "react-icons/md";
import SideNav from "../components/SideNav";

const AllPatients = () => {
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
  console.log(patients);

  return (
    <div className="charts">
      <SideNav />
      <table className="titleMargin">
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Phone Number</th>
          <th>Nationality</th>
        </tr>
        {patients.map(
          (patient, i) =>
            patient.user !== null && (
              <tr key={i}>
                <td>{patient.user.name} </td>
                <td>{patient.user.email}</td>
                <td>{patient.phone_number} </td>
                <td>{patient.nationality}</td>
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
  );
};
export default AllPatients;
