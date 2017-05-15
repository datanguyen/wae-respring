import { combineReducers } from 'redux'
import { dashboard, food } from './pages'

export const AppReducer = combineReducers({
    dashboard, food
})
