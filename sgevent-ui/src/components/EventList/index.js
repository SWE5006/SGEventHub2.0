import React, { useEffect } from "react";
import {
  useGetEventListQuery,
  useDeleteEventMutation,
  useRegisterEventMutation,
} from "../../services/event.service";
import EventCard from "../EventCard";
import Box from "@mui/material/Box";
import { navigate } from "gatsby";
import PropTypes from "prop-types";

const EventList = ({ isAdmin }) => {
  const { data, error, isFetching, refetch } = useGetEventListQuery(null, {
    refetchOnMountOrArgChange: true,
  });
  const [deleteEvent, deleteResult] = useDeleteEventMutation();
  const [registerEvent, registerResult] = useRegisterEventMutation();

  useEffect(() => {
    if (deleteResult.isSuccess || registerResult.isSuccess) refetch();
  }, [registerResult, deleteResult]);

  //正确
  const onEdit = (eventId) => {
    navigate(`/events/edit?eventid=${eventId}`);
  };

  //正确
  const onDetails = (eventId) => {
    navigate(`/events/details?eventid=${eventId}`);
  };

  return (
    <Box
      sx={{
        flexGrow: 1,
        display: "flex",
        justifyContent: "space-between",
        flexWrap: "wrap",
      }}
    >
      {data?.map((item) => (
        <EventCard
          key={item.eventId} // 添加 key 属性
          value={item}
          onDelete={deleteEvent}
          isDeleting={deleteResult.isLoading} // 修正属性名
          onEdit={onEdit}
          onRegister={registerEvent}
          isAdmin={isAdmin}
          onDetails={onDetails}
          isRegistering={
            registerResult?.originalArgs?.eventId === item.eventId
              ? registerResult.isLoading || isFetching
              : false
          }
        />
      ))}
    </Box>
  );
};

EventList.propTypes = {
  isAdmin: PropTypes.bool.isRequired,
};

export default EventList;
