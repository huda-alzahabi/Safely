import React from "react";
import UsersPieChart from "../components/UsersPieChart";
import SpecialtiesPieChart from "../components/SpecialtiesPieChart";
import SideNav from "../components/SideNav";
import { TbLogout } from "react-icons/tb";
import { useNavigate } from "react-router-dom";

const Statistics = () => {
  const nav = useNavigate();

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
      <UsersPieChart />
      <SpecialtiesPieChart />
    </div>
    </>
  );
};

export default Statistics;
