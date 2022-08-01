import React, { useState, useEffect } from "react";
import Chart from "react-apexcharts";

const SpecialtiesPieChart = () => {
const  [series,setSeries] = useState([]);
const  [label,setLabel] = useState([]);

  const getSpecialties = async () => {
    const res = await fetch("http://127.0.0.1:3030/api/doctor/specialties", {
      headers: {
        "content-type": "application/json",
        token: localStorage.getItem("Bearer"),
      },
    });
    const data = await res.json();
    const array1 = data.map((specialty) => specialty.count);
    const array2 = data.map((specialty) => specialty._id);
    setSeries(array1);
    setLabel(array2);
  };

  useEffect(() => {
    getSpecialties();
  }, []);

  const options = { labels: label };

  return (
    <div className="donut">
      <div className="doctors_title"> Doctors per Specialty </div>
      <Chart
        className="chart"
        options={options}
        series={series}
        type="pie"
        width="400"
      />
    </div>
  );
};

export default SpecialtiesPieChart;
