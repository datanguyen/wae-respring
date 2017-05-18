import { combineReducers } from 'redux'
import { dashboard, food, electronic, handmade } from './pages'
import { authenticate, cart } from './common'

export const AppReducer = combineReducers({
    dashboard, food, electronic, handmade, authenticate, cart
})
