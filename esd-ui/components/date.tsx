import { parseISO, format } from 'date-fns';

export default function Date({ dateString }: IDateProps): JSX.Element {
  const date: Date = parseISO(dateString);
  return <time dateTime={dateString}>{format(date, 'LLLL d, yyyy')}</time>;
}

export interface IDateProps {
  dateString: string;
}
