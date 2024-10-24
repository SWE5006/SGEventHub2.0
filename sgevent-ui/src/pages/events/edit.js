import React, { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import AdminPageLayout from "../../components/AdminPageLayout";
import {
  useGetEventDetailsQuery,
  useUpdateEventMutation,
  useRegisterEventMutation,
} from "../../services/event.service";
import { navigate } from "gatsby";
import EditEventForm from "../../components/EditEventForm";
import { useGetUserListByIdsMutation } from "../../services/user.service";
import Button from "@mui/material/Button";

export default function EditEvent({ location }) {
  const params = new URLSearchParams(location.search);
  const eventId = params.get("eventid");

  const { data, isFetching } = useGetEventDetailsQuery(eventId);
  const [updateEvent, result] = useUpdateEventMutation();
  const [registerEvent, registerResult] = useRegisterEventMutation();
  const [getUserList, userListResult] = useGetUserListByIdsMutation();
  const [type, setType] = useState("view");
  const [value, setValue] = useState({});
  const [userList, setUserList] = useState([]);

  useEffect(() => {
    if (result.isSuccess) navigate("/events");
  }, [result.isSuccess]);

  useEffect(() => {
    //reset userlist when user click on close button on participants
    if (registerResult.isSuccess) {
      const userId = registerResult.originalArgs.userId;
      setUserList((prev) => {
        const userList = [...prev];
        const index = userList.findIndex((user) => user.userId === userId);
        userList.splice(index, 1);
        return userList;
      });
    }
  }, [registerResult]);

  useEffect(() => {
    //get userList data when user detail is available
    setValue(data);
    if (data?.userList?.length) {
      const list = data?.userList.map((item) => item.userId);
      getUserList(list);
    }
  }, [data]);

  useEffect(() => {
    setUserList(userListResult.data);
  }, [userListResult]);

  const onUpdateUser = (event) => {
    updateEvent(event);
  };
  return (
    <Layout isLoading={isFetching}>
      <AdminPageLayout
        title={type === "view" ? "Event Details" : "Edit Event"}
        rightEl={
          <Button
            variant="contained"
            onClick={() => {
              setType((prev) => (prev === "view" ? "edit" : "view"));
            }}
          >
            {type === "view" ? "Edit Event Info" : "View Event Details"}
          </Button>
        }
      >
        <EditEventForm
          type={type}
          value={value}
          userList={userList}
          onSubmit={onUpdateUser}
          onDelete={registerEvent}
          isDeleting={registerResult.isFetching}
          isUpdating={result.isLoading}
          isError={result.isError}
        />
      </AdminPageLayout>
    </Layout>
  );
}
