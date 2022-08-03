import React from "react";
import { useState, useEffect } from "react";
import { FaEdit } from "react-icons/fa";
import { MdDeleteForever } from "react-icons/md";
import SideNav from "../components/SideNav";

const Hospitals = () => {
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

  return (
    <div className="charts">
      <SideNav />
      <table className="titleMargin">
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Location</th>
          <th>Clinics Working Hours</th>
        </tr>
        {hospitals.map(
          (hospital, i) =>
            hospital.user !== null && (
              <tr key={i}>
                <td>{hospital.user.name} </td>
                <td>
                  {hospital.address.city}, {hospital.address.country}
                </td>
                <td>{hospital.phone_number}</td>
                <td>{hospital.outpatient_clinic}</td>
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
export default Hospitals;
