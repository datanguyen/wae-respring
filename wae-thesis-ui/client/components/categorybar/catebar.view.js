import React from "react"
import cssModules from "react-css-modules"
import style from "./catebar.style.scss"
import map from "lodash/map"

import {CateBarElement} from "./catebar.element"
import data from "./catebar.data"

@cssModules(style, {errorWhenNotFound: false})
export class CateBarView extends React.Component {
    render() {
        const categories = map(data, (val, myKey) => <CateBarElement header={val} myKey={myKey} />)

        return (
          <div>
              {categories}
          </div>
        )
    }
}
