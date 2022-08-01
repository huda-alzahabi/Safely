import React, { useState } from "react";
import { useEffect } from "react";
import Chart from "react-apexcharts";

const UsersPieChart = () => {
  const [patients_count, setPatientsCount] = useState(0);
  const [doctors_count, setDoctorsCount] = useState(0);
  const [hospitals_count, setHospitalsCount] = useState(0);

  const getPatientsCount = async () => {
    const res = await fetch("http://127.0.0.1:3030/api/patient/count", {
      headers: {
        "content-type": "application/json",
        token: localStorage.getItem("Bearer"),
      },
    });
    const data = await res.json();
    console.log(data["patients"]);
    setPatientsCount(data["patients"]);
    return data;
  };
  const getDoctorsCount = async () => {
    const res = await fetch("http://127.0.0.1:3030/api/doctor/count", {
      headers: {
        "content-type": "application/json",
        token: localStorage.getItem("Bearer"),
      },
    });
    const data = await res.json();
    console.log(data["doctors"]);
    setDoctorsCount(data["doctors"]);
    return data;
  };
  const getHospitalsCount = async () => {
    const res = await fetch("http://127.0.0.1:3030/api/hospital/count", {
      headers: {
        "content-type": "application/json",
        token: localStorage.getItem("Bearer"),
      },
    });
    const data = await res.json();
    console.log(data["hospitals"]);
    setHospitalsCount(data["hospitals"]);
    return data;
  };

  useEffect(() => {
    getPatientsCount();
    getDoctorsCount();
    getHospitalsCount();
  }, []);

  const options = { labels: ["Patients", "Doctors", "Hospitals"] };
  const series = [patients_count, doctors_count, hospitals_count];

  return (
    <div className="donut">
      <div className="users_title"> Users </div>
      <Chart className="chart" options={options} series={series} type="pie" width="400" />
    </div>
  );
};

export default UsersPieChart;
