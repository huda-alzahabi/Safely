import { useState } from "react";
import "rsuite/dist/rsuite.min.css";
import { Nav, Sidenav } from "rsuite/";
import UserCircle from "@rsuite/icons/legacy/UserCircle";
import HospitalO from "@rsuite/icons/legacy/HospitalO";
import logo from "../assets/logo.png";
import UserMd from "@rsuite/icons/legacy/UserMd";
import Bed from "@rsuite/icons/legacy/Bed";
import Percent from "@rsuite/icons/legacy/Percent";
import {useNavigate } from "react-router-dom";

const SideNav = () => {
  const [expand, setExpand] = useState(false);
  const [activeKey, setActiveKey] = useState("1");
  let nav = useNavigate();

  function goToPatients() {
    nav("/patients");
  }
  function goToUsers() {
    nav("/users");
  }
  function goToHospitals() {
    nav("/hospitals");
  }
  function goToDoctors() {
    nav("/doctors");
  }
  function goToStats() {
    nav("/statistics");
  }
  return (
    <div>
      <img className="logo" src={logo} alt="" />
      <div className="sidenav">
        <Sidenav className="item" expanded={expand} appearance="inverse ">
          <Sidenav.Body className="item">
            <Nav activeKey={activeKey} onSelect={setActiveKey}>
              <Nav.Item
                className="item"
                eventKey="1"
                icon={<Percent />}
                onClick={goToStats}
              >
                Statistics
              </Nav.Item>
              <Nav.Item
                className="item"
                eventKey="2"
                icon={<UserCircle />}
                onClick={goToUsers}
              >
                Users
              </Nav.Item>
              <Nav.Item
                className="item"
                eventKey="3"
                icon={<HospitalO />}
                onClick={goToHospitals}
              >
                Hospitals
              </Nav.Item>
              <Nav.Item
                className="item"
                eventKey="4"
                icon={<Bed />}
                onClick={goToPatients}
              >
                Patients
              </Nav.Item>
              <Nav.Item
                className="item"
                eventKey="5"
                icon={<UserMd />}
                onClick={goToDoctors}
              >
                Doctors
              </Nav.Item>
            </Nav>
          </Sidenav.Body>
          <Sidenav.Toggle
            className="item"
            expanded={expand}
            onToggle={(expanded) => setExpand(expanded)}
          />
        </Sidenav>
      </div>
    </div>
  );
};
export default SideNav;
