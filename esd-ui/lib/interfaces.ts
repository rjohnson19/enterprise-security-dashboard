// common interfaces
export interface IAdvisoryListItem {
    readonly id: string;
    readonly name: string;
    readonly date: Date;
    readonly externalUrl: string;
    readonly vendor: string;
    readonly product: string;
    readonly severity?: string;
    readonly status: ADVISORY_STATUS;
}

export enum ADVISORY_STATUS {
    NEW = 'NEW',
    PENDING = 'PENDING',
    ADDRESSED = 'ADDRESSED',
    UNAFFECTED = 'UNAFFECTED',
    FIX_OPTIONAL = 'FIX_OPTIONAL',
    WONTFIX = 'WONTFIX'
}
