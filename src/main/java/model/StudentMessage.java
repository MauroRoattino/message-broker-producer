package model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "StudentMessage", description = "This Enrollment is retrieved from the " +
        "[Admission API](https://api.ues21.edu.ar/admission-api/api/swagger-ui.html#/default-enrollment-controller/getContextUsingGET)")
@Data
@Builder
@NoArgsConstructor
public class StudentMessage {

        @ApiModelProperty(value = "the name of the student")
        private String name;

        @ApiModelProperty(value = "the last name of the student")
        private String lastName;

        @ApiModelProperty(value = "the student id")
        private String studentRecord;

        @ApiModelProperty(value = "the student message")
        private String message;

        public StudentMessage(String name, String lastName, String studentRecord, String message) {
                this.name = name;
                this.lastName = lastName;
                this.studentRecord = studentRecord;
                this.message = message;

}
        public void setName(String name) {
                this.name = name;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getStudentRecord() {
                return studentRecord;
        }

        public void setStudentRecord(String studentRecord) {
                this.studentRecord = studentRecord;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public String getName() {
                return name;
        }
}
