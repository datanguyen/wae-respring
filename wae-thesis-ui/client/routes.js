import {App} from './app'
import {Dashboard, Home, HomeRoute, Explore,  DashboardRoute, ExploreRoute, AboutRoute, NotFoundRoute, NotFound} from './pages'

export const AppRoute = {
    childRoutes: [
        {
            path: '/',
            component: App,
            indexRoute: {
                component: Home
            },
            childRoutes: [
                HomeRoute,
                ExploreRoute,
                AboutRoute,
                NotFoundRoute
            ]
        },
        {
            path: '/error',
            component: NotFound
        }
    ]
}
