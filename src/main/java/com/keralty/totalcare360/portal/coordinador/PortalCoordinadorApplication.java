package com.keralty.totalcare360.portal.coordinador;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
        tags = {
                @Tag(name="Activity", description="Activity APIs"),
                @Tag(name="Patient", description="Patient APIs"),
                @Tag(name="PatientManagement", description="Patient Management APIs")
        },
        info = @Info(
                title="Portal Coordinador API",
                version = "1.0.1",
                contact = @Contact(
                        name = "API Support",
                        url = "http://exampleurl.com/contact",
                        email = "techsupport@example.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class PortalCoordinadorApplication extends Application{
}
