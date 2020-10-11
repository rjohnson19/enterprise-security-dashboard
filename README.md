# Enterprise Security Dashboard
Provides a unified view of security alerts from third-party vendors to allow evaluation and remediation.

# About
## Rationale
- Software companies use libraries, frameworks, and other tools from numerous third-party vendors.
- These third-party software products are periodically updated to address security vulnerabilities.
- Announcements about these vulnerabilities are found in individual sites, feeds, mailing lists, etc. depending on the vendor.
- IT Security and DevOps team members need a single place to view all vulnerabilities in software utilized by their organization.
- They then need the ability to track how they acted on a potential vulnerability, for example finding it doesn't apply, or that it was resolved by an upgrade; along with the ability to link to evidence of the resolution, such as a Jira or Pull Request where the work was done internally.

## Features - v1
1. Basic Authentication and authorization with a standard user role and an administrator role.
2. Ability to view security advisories for each supported vendor and software product.
3. Ability to import each advisory into the application to take action on it.
4. Ability to resolve each imported advisory with a status and message.
5. Statuses and resolution of advisories is reflected in the 'home' view of advisories, for example advisories we've resolved provide a visual indication of that.
6. Persistence of all imported advisories and status / messages across restarts of the application.

## Example Use Case
1. As a DevOps Engineer, I am tasked with checking the Enterprise Security Dashboard on a monthly basis to detect security advisories we need to address.
2. I open the Dashboard and Login with my Username and Password.
3. I review the table of advisories, which can be filtered by vendor or software product.
4. I see there are advisories in a NEW state with a visual indication, so I click an Evaluate action which changes it to the PENDING state.
5. I can then click the ID of the advisory to view details on it, such as what versions are affected, and the severity. I can also click a link to the advisory on the vendor site.
6. I can enter a message such as 'we are not affected, due to using newer version', and use a dropdown to resolve the advisory as UNAFFECTED.
7. Returning to the home view, I now see the advisory is crossed out with a status of UNAFFECTED.

# Supported Vendors and Software
- This is currently a Proof of Concept under development.
- Initially, will plan to support vendors and software related to how I plan to build this application, which will include those commonly used for Java shops: Amazon (Corretto, Amazon Linux), VMware Tanzu [AKA Pivotal] (Spring Boot, Spring Framework, Spring Security)
