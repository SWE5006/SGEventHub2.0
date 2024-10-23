import React from "react";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import PropTypes from "prop-types";

const AdminPageLayout = ({ title, rightEl, children }) => {
  return (
    <>
      <Box display="flex" justifyContent="space-between">
        <Typography variant="h4" gutterBottom>
          {title}
        </Typography>
        <Box display="flex" alignItems="center">
          {rightEl}
        </Box>
      </Box>
      {children}
    </>
  );
};

AdminPageLayout.propTypes = {
  title: PropTypes.string.isRequired,
  rightEl: PropTypes.element.isRequired,
  children: PropTypes.any,
};

export default AdminPageLayout;
