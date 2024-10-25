import * as React from "react";
import Header from "../Header";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import FormSkeleton from "../FormSkeleton";
import PropTypes from "prop-types";

export default function Layout({ children, isLoading }) {
  return (
    <Box>
      <Header />
      <br />
      <Container>{isLoading ? <FormSkeleton /> : children}</Container>
    </Box>
  );
}

Layout.propTypes = {
  children: PropTypes.element,
  isLoading: PropTypes.bool.isRequired,
};
