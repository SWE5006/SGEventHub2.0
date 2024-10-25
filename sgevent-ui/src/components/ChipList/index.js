import React from "react";
import Chip from "@mui/material/Chip";
import Stack from "@mui/material/Stack";
import PropTypes from "prop-types";

const ChipList = ({ eventId, items, onDelete, disabled, isDeleting }) => {
  const handleDelete = (item) => () => {
    onDelete({
      type: "unregister",
      eventId,
      userId: item.userId,
    });
  };

  return (
    <Stack direction="row" spacing={1} sx={{ marginTop: 1 }}>
      {(items || []).map((item) => (
        <Chip
          disabled={disabled || isDeleting}
          variant="outlined"
          color="primary"
          key={item.userId}
          label={item.userName}
          onDelete={handleDelete(item)}
        />
      ))}
    </Stack>
  );
};

ChipList.propTypes = {
  eventId: PropTypes.string.isRequired,
  items: PropTypes.arrayOf(
    PropTypes.shape({ userId: PropTypes.string, userName: PropTypes.string })
  ).isRequired,
  onDelete: PropTypes.func,
  disabled: PropTypes.bool.isRequired,
  isDeleting: PropTypes.bool.isRequired,
};

export default ChipList;
