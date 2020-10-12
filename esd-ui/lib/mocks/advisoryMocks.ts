import { ADVISORY_STATUS, IAdvisoryListItem } from "../interfaces";

export const mockAdvisories: IAdvisoryListItem[] = [
  {
    id: "CVE-2020-5422",
    name: "UAA password may appear in Operations Manager process arguments ",
    date: new Date("2020-01-01"),
    externalUrl: "https://tanzu.vmware.com/security/cve-2020-5422",
    vendor: "VMWare",
    product: "Operations Manager",
    severity: "High",
    status: ADVISORY_STATUS.NEW
  },
  {
    id: "CVE-2020-5421",
    name: "RFD Protection Bypass via jsessionid",
    date: new Date("2020-09-17"),
    externalUrl: "https://tanzu.vmware.com/security/cve-2020-5422",
    vendor: "VMWare",
    product: "Spring Framework",
    severity: "High",
    status: ADVISORY_STATUS.PENDING
  },
  {
    id: "CVE-2020-5420",
    name: "Gorouter is vulnerable to DoS attack via invalid HTTP responses",
    date: new Date("2020-09-10"),
    externalUrl: "https://tanzu.vmware.com/security/cve-2020-5420",
    vendor: "VMWare",
    product: "VMware Tanzu Application Service for VMs and Isolation Segment",
    severity: "High",
    status: ADVISORY_STATUS.UNAFFECTED
  }
];
