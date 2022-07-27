import React from "react";
import "./topbar.css";
import { NotificationsNone, Language, Settings } from "@material-ui/icons";

export default function Topbar() {
  return (
    <div className="topbar">
      <div className="topbarWrapper">
        <div className="topLeft">
          <span className="logo">Admin Management</span>
        </div>
        <div className="topRight">
          <img src="/image/admin.png" alt="" className="topAvatar" />
        </div>
      </div>
    </div>
  );
}
