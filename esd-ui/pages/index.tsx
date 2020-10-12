import Head from 'next/head';
import Layout, { siteTitle } from '../components/layout';
import { getSortedPostsData, IPostData } from '../lib/posts';
import utilStyles from '../styles/utils.module.css';
import { GetStaticProps } from 'next';
import AdvisoryTable from '../components/advisory-table';
import { mockAdvisories } from '../lib/mocks/advisoryMocks';

export const getStaticProps: GetStaticProps = async () => {
  const allPostsData = getSortedPostsData();
  return {
    props: {
      allPostsData
    }
  };
};

export default function Home({ allPostsData }: IHomeProps): JSX.Element {
  return (
    <Layout home>
      <Head>
        <title>{siteTitle}</title>
      </Head>
      <section className={utilStyles.headingMd}>
        <p>Welcome to the advisories dashboard.</p>
        <p>
          Below you will see a list of the latest advisories.
        </p>
      </section>
      <section className={utilStyles.headingMd}>…</section>
      <section className={`${utilStyles.padding1px}`}>
        <h2 className={utilStyles.headingLg}>Advisories</h2>
        <AdvisoryTable items={mockAdvisories} />
      </section>
    </Layout>
  );
}

export interface IHomeProps {
  allPostsData: IPostData[];
}
