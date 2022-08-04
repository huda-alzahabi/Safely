import { useState } from "react";
import "rsuite/dist/rsuite.min.css";
import { Nav, Sidenav } from "rsuite/";
import UserCircle from "@rsuite/icons/legacy/UserCircle";
import HospitalO from "@rsuite/icons/legacy/HospitalO";
import logo from "../assets/logo.png";
import UserMd from "@rsuite/icons/legacy/UserMd";
import Bed from "@rsuite/icons/legacy/Bed";
import Percent from "@rsuite/icons/legacy/Percent";
import { useNavigate } from "react-router-dom";

const SideNav = () => {
  const [expand, setExpand] = useState(false);
  const [activeKey, setActiveKey] = useState("1");
  let nav = useNavigate();

  function navigateTo(link) {
    nav(link);
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
                eventKey="/statistics"
                icon={<Percent />}
                onClick={() => navigateTo("/statistics")}
              >
                Statistics
              </Nav.Item>
              <Nav.Item
                className="item"
                eventKey="/users"
                icon={<UserCircle />}
                onClick={() => navigateTo("/users")}
              >
                Users
              </Nav.Item>
              <Nav.Item
                className="item"
                eventKey="/hospitals"
                icon={<HospitalO />}
                onClick={() => navigateTo("/hospitals")}
              >
                Hospitals
              </Nav.Item>
              <Nav.Item
                className="item"
                eventKey="/patients"
                icon={<Bed />}
                onClick={() => navigateTo("/patients")}
              >
                Patients
              </Nav.Item>
              <Nav.Item
                className="item"
                eventKey="/doctors"
                icon={<UserMd />}
                onClick={() => navigateTo("/doctors")}
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
