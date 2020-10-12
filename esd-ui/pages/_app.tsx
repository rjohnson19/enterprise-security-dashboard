import { AppProps } from 'next/app';
import 'semantic-ui-css/semantic.min.css';
import '../styles/global.css';

export default function App({ Component, pageProps }: AppProps): JSX.Element {
  return <Component {...pageProps} />;
}
