import * as React from "react";
import dayjs from "dayjs";
import { DemoContainer, DemoItem } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";

import { DateTimeRangePicker } from "@mui/x-date-pickers-pro/DateTimeRangePicker";
import PropTypes from "prop-types";

export default function DateTimeRangePickerInput({
  defaultStartVal,
  defaultEndVal,
  label,
  onChange,
  disabled,
}) {
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <DemoContainer
        components={["DateTimeRangePicker", "DateTimeRangePicker"]}
      >
        <DemoItem label={label} component="DateTimeRangePicker">
          <DateTimeRangePicker
            disabled={disabled}
            defaultValue={[dayjs(defaultStartVal), dayjs(defaultEndVal)]}
            onChange={onChange}
          />
        </DemoItem>
      </DemoContainer>
    </LocalizationProvider>
  );
}

DateTimeRangePickerInput.propTypes = {
  defaultStartVal: PropTypes.string.isRequired,
  defaultEndVal: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  onChange: PropTypes.func,
  disabled: PropTypes.bool.isRequired,
};
