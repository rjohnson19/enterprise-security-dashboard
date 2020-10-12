import { IAdvisoryListItem } from "../lib/interfaces";
import { Label, Table } from 'semantic-ui-react';
import Date from "./date";

export default function AdvisoryTable({ items }: IAdvisoryTableProps): JSX.Element {
  return (
    <Table celled>
      <Table.Header>
        <Table.Row>
          <Table.HeaderCell>ID</Table.HeaderCell>
          <Table.HeaderCell>Name</Table.HeaderCell>
          <Table.HeaderCell>Date</Table.HeaderCell>
          <Table.HeaderCell>Severity</Table.HeaderCell>
          <Table.HeaderCell>Vendor</Table.HeaderCell>
          <Table.HeaderCell>Product</Table.HeaderCell>
          <Table.HeaderCell>Status</Table.HeaderCell>
        </Table.Row>
      </Table.Header>
      <Table.Body>
        {items.map(toTableRow)}
      </Table.Body>
    </Table>
  );
}

function toTableRow(item: IAdvisoryListItem): JSX.Element {
  return (
    <Table.Row key={item.id}>
      <Table.Cell>
        <Label ribbon>{item.id}</Label>
      </Table.Cell>
      <Table.Cell>{item.name}</Table.Cell>
      <Table.Cell><Date dateString={item.date.toISOString()} /></Table.Cell>
      <Table.Cell>{item.severity}</Table.Cell>
      <Table.Cell>{item.vendor}</Table.Cell>
      <Table.Cell>{item.product}</Table.Cell>
      <Table.Cell>{item.status}</Table.Cell>
    </Table.Row>
  );
}

export interface IAdvisoryTableProps {
    readonly items: IAdvisoryListItem[];
}
