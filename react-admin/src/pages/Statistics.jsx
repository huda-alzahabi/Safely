import React from "react";
import UsersPieChart from "../components/UsersPieChart";
import SpecialtiesPieChart from "../components/SpecialtiesPieChart";
import SideNav from "../components/SideNav";
const Statistics = () => {
  return (
    <div className="charts">
      <SideNav />
      <UsersPieChart />
      <SpecialtiesPieChart />
    </div>
  );
};

export default Statistics;
