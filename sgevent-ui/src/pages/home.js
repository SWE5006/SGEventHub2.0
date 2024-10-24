import React from "react";
import { authSelector } from "../state/auth/slice";
import { useSelector } from "react-redux";
import { HOME_MAPPING } from "../constants";
import { navigate } from "gatsby";

export default function Home() {
  const { isLoggedIn, userInfo } = useSelector((state) => authSelector(state));

  React.useEffect(() => {
    if (isLoggedIn && userInfo?.roleId) {
      const link = HOME_MAPPING[userInfo?.roleId];
      navigate(link);
    } else {
      navigate("/login"); // 如果未登录或用户信息不完整，重定向到登录页面
    }
  }, [isLoggedIn, userInfo]);

  return null;
}
