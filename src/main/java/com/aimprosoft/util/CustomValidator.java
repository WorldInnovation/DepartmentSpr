package com.aimprosoft.util;


import com.aimprosoft.exeption.ValidateExp;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CustomValidator {
    @Autowired
    private Validator validator ;

    public void validate(Object object) throws ValidateExp {
        List<ConstraintViolation> constraintViolations = validator.validate(object);
        if (constraintViolations.size() > 0) {

            Map<String, String> errorMap = new HashMap<String, String>();

            for (ConstraintViolation violation : constraintViolations) {
                OValContext context = violation.getContext();
                if (context instanceof FieldContext) {
                    FieldContext fieldContext = (FieldContext) context;
                    Field field = fieldContext.getField();
                    String fieldName = field.getName();
                    String errorMessage = violation.getMessage();
                    errorMap.put(fieldName, errorMessage);
                }
            }
            throw new ValidateExp(errorMap);

        }
    }
}

    /*    public Map<String,List<String>> getErrorMap(){
            return errorMap;}

        public ValidationException(Map<String,List<String>> errorMap){
            this.errorMap = errorMap;
        }
            */



 /*      // ConstraintViolation constraintViolations ;
       String strValidate =  constraintViolations.toString();
       strValidate =  strValidate.substring(67);
       System.out.println(strValidate);
        //constraintViolations.get(0).getMessage();

        int size = constraintViolations.size();


        for(int i =0;i>size; i++  ){
            strConstrain = constraintViolations.toString();
            int strLen = strConstrain.length();
           // strConstrain = strConstrain.;


        }
        System.out.println(strConstrain);
        String strMessage = constraintViolations.get(1).getMessage();*/


/*    public static void showConstraintViolation(ConstraintViolationException e)
    {
        // build constraint error
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations())
        {
            sb.append("Error: " + violation.getPropertyPath() + " : " + violation.getMessage() + "\n");

        }
        logger.error(sb.toString());
        Notification.show(sb.toString(), Type.ERROR_MESSAGE);
    }*/


//
//
//
//    // configure OVal to interprete OVal constraint annotations as well as EJB3 JPA annotations
//    Validator validator = new Validator();
//
//    Employee entity = new Employee();
//
//    entity.id ="12345"; // violation - the max length is 4
//    entity.descr =null; // violation - cannot be null
//    entity.parent =null; // violation - cannot be null varankinviv@gmail.com
//
//    // collect the constraint violations
//    List<ConstraintViolation> violations = validator.validate(entity);


